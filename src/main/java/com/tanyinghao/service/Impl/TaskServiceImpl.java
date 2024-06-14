package com.tanyinghao.service.Impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.comm.constant.ScheduleConstant;
import com.tanyinghao.comm.enums.TaskStatusEnum;
import com.tanyinghao.comm.exception.ServiceException;
import com.tanyinghao.comm.utils.BeanCopyUtils;
import com.tanyinghao.quartz.utils.CronUtils;
import com.tanyinghao.comm.utils.PageUtils;
import com.tanyinghao.mapper.TaskMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.StatusDTO;
import com.tanyinghao.model.dto.TaskDTO;
import com.tanyinghao.model.dto.TaskRunDTO;
import com.tanyinghao.model.entity.Task;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.TaskBackVO;
import com.tanyinghao.quartz.utils.ScheduleUtils;
import com.tanyinghao.service.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TaskServiceImpl
 * @Description 定时任务服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/13 12:10
 * @Version 1.0
 **/
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private Scheduler scheduler;

    /**
     *
     * @Author TanYingHao
     * @Description 项目启动时，启动定时任务
     * @Date 9:46 2024/6/14
     * @Param []
     * 注：这里注解的作用是在构造函数完成，bean自动依赖注入之后才会自动执行注释修饰的方法。
     **/
    @PostConstruct
    public void init() throws SchedulerException {
        scheduler.clear();
        List<Task> taskList = taskMapper.selectList(null);
        // 创建定时任务
        taskList.forEach(task -> ScheduleUtils.createScheduleJob(scheduler, task));
    }

    @Override
    public PageResult<TaskBackVO> listTaskBackVO(ConditionDTO condition) {
        // 查询定时任务数量
        Long count = taskMapper.countTaskBackVO(condition);
        if (count == 0) {
            return new PageResult<>();
        }
        // 分页查询定时任务列表
        List<TaskBackVO> taskBackVOList = taskMapper.selectTaskBackVO(PageUtils.getLimit(), PageUtils.getSize(), condition);
        // 设置下一个有效时间
        taskBackVOList.forEach(item -> {
            if (StringUtils.isNotEmpty(item.getCronExpression())) {
                Date nextExecution = CronUtils.getNextExecution(item.getCronExpression());
                item.setNextValidTime(nextExecution);
            } else {
                item.setNextValidTime(null);
            }
        });
        return new PageResult<>(taskBackVOList, count);
    }

    @Override
    public void addTask(TaskDTO task) {
        Assert.isTrue(CronUtils.isValid(task.getCronExpression()), "Cron表达式无效");
        Task newTask = BeanCopyUtils.copyBean(task, Task.class);
        // 新增定时任务
        int row = taskMapper.insert(newTask);
        // 创建定时任务
        if (row > 0) {
            ScheduleUtils.createScheduleJob(scheduler, newTask);
        }

    }

    @Override
    public void updateTask(TaskDTO task) {
        Assert.isTrue(CronUtils.isValid(task.getCronExpression()), "Cron表达式无效");
        Task existTask = taskMapper.selectById(task.getId());
        Task newTask = BeanCopyUtils.copyBean(task, Task.class);
        // 更新定时任务
        int row = taskMapper.updateById(newTask);
        if (row > 0) {
            try {
                updateSchedulerJob(newTask, existTask.getTaskGroup());
            } catch (SchedulerException e) {
                throw new ServiceException("更新定时任务异常");
            }
        }
    }
    
    /**
     *
     * @Author TanYingHao
     * @Description 更新任务
     * @Date 9:10 2024/6/14
     * @Param [newTask, taskGroup] 任务对象 任务组名
     **/
    private void updateSchedulerJob(Task newTask, String taskGroup) throws SchedulerException {
        Integer taskId = newTask.getId();
        // 判断是否存在
        JobKey jobKey = ScheduleUtils.getJobKey(taskId, taskGroup);
        if (scheduler.checkExists(jobKey)) {
            scheduler.deleteJob(jobKey);
        }
        ScheduleUtils.createScheduleJob(scheduler, newTask);
    }

    @Override
    public void deleteTask(List<Integer> taskIdList) {
        List<Task> taskList = taskMapper.selectList(new LambdaQueryWrapper<Task>()
                .select(Task::getId, Task::getTaskGroup)
                .in(Task::getId, taskIdList));
        // 删除定时任务
        int row = taskMapper.delete(new LambdaQueryWrapper<Task>().in(Task::getId, taskList));
        if (row > 0) {
            taskList.forEach(task -> {
                try {
                    scheduler.deleteJob(ScheduleUtils.getJobKey(task.getId(), task.getTaskGroup()));
                } catch (SchedulerException e) {
                    throw new ServiceException("删除定时任务异常");
                }
            });
        }
    }

    @Override
    public void updateTaskStatus(StatusDTO taskStatus) {
        Task task = taskMapper.selectById(taskStatus.getId());
        // 相同不用更新
        if (task.getStatus().equals(taskStatus.getStatus())) {
            return ;
        }
        // 更新数据库中的定时任务状态
        Task newTask = Task.builder()
                .id(taskStatus.getId())
                .status(taskStatus.getStatus())
                .build();
        int row = taskMapper.updateById(newTask);
        // 获取定时任务状态、id和任务组
        Integer status = taskStatus.getStatus();
        Integer id = task.getId();
        String taskGroup = task.getTaskGroup();
        if (row > 0) {
            try {
                // 更新定时任务
                if (TaskStatusEnum.RUNNING.getStatus().equals(status)) {
                    scheduler.resumeJob(ScheduleUtils.getJobKey(id, taskGroup));
                }
                if (TaskStatusEnum.PAUSE.getStatus().equals(status)) {
                    scheduler.pauseJob(ScheduleUtils.getJobKey(id, taskGroup));
                }
            } catch (SchedulerException e) {
                throw new ServiceException("更新定时任务状态异常");
            }
        }
    }

    @Override
    public void runTask(TaskRunDTO taskRun) {
        // 查询定时任务
        Task task = taskMapper.selectById(taskRun.getId());
        // 设置参数
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(ScheduleConstant.TASK_PROPERTIES, task);
        try {
            scheduler.triggerJob(ScheduleUtils.getJobKey(taskRun.getId(), taskRun.getTaskGroup()), jobDataMap);
        } catch (SchedulerException e) {
            throw new ServiceException("执行定时任务异常");
        }
    }
}
