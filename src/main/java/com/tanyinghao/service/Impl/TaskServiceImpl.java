package com.tanyinghao.service.Impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
import com.tanyinghao.service.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    }

    @Override
    public void updateTask(TaskDTO task) {

    }

    @Override
    public void deleteTask(List<Integer> taskIdList) {

    }

    @Override
    public void updateTaskStatus(StatusDTO taskStatus) {

    }

    @Override
    public void runTask(TaskRunDTO taskRun) {

    }
}
