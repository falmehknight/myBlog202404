package com.tanyinghao.quartz.execution;

import com.tanyinghao.comm.constant.ScheduleConstant;
import com.tanyinghao.comm.utils.SpringUtils;
import com.tanyinghao.mapper.TaskLogMapper;
import com.tanyinghao.model.entity.Task;
import com.tanyinghao.model.entity.TaskLog;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Date;

import static com.tanyinghao.comm.constant.CommConstant.FALSE;
import static com.tanyinghao.comm.constant.CommConstant.TRUE;

/**
 * @ClassName AbstractQuartzJob
 * @Description 抽象quartz调用
 * @Author 谭颍豪
 * @Date 2024/6/13 19:40
 * @Version 1.0
 **/
public abstract class AbstractQuartzJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(AbstractQuartzJob.class);

    // 线程本地变量
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) {
        Task task = new Task();
        BeanUtils.copyProperties(context.getMergedJobDataMap().get(ScheduleConstant.TASK_PROPERTIES), task);
        try {
            before(context, task);
            doExecute(context, task);
            after(context, task, null);
        } catch (Exception e) {
            log.error("任务执行异常 - ： ", e);
            after(context, task, e);
        }
    }

    /**
     *
     * @Author TanYingHao
     * @Description 执行后
     * @Date 19:55 2024/6/13
     * @Param [context, task, e] 工作执行上下文，计划任务，异常
     **/
    private void after(JobExecutionContext context, Task task, Exception e) {
        Date startTime = threadLocal.get();
        threadLocal.remove();
        final TaskLog taskLog = new TaskLog();
        taskLog.setTaskName(task.getTaskName());
        taskLog.setTaskGroup(task.getTaskGroup());
        taskLog.setInvokeTarget(task.getInvokeTarget());
        long runTime = new Date().getTime() - startTime.getTime();
        taskLog.setTaskMessage(taskLog.getTaskName() + "总共耗时：" + runTime + "毫秒");
        if (e != null) {
            taskLog.setStatus(FALSE);
            String errorMsg = StringUtils.substring(e.getMessage(), 0, 2000);
            taskLog.setErrorInfo(errorMsg);
        } else {
            taskLog.setStatus(TRUE);
        }
        // 写到数据库中
        SpringUtils.getBean(TaskLogMapper.class).insert(taskLog);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 执行方法，由子类重写
     * @Date 19:50 2024/6/13
     * @Param [context, task] 工作执行上下文，计划任务
     **/
    protected abstract void doExecute(JobExecutionContext context, Task task) throws Exception;

    /**
     *
     * @Author TanYingHao
     * @Description 执行前的操作
     * @Date 19:49 2024/6/13
     * @Param [context, task] 工作执行上下文，计划任务
     **/
    private void before(JobExecutionContext context, Task task) {
        threadLocal.set(new Date());
    }
}
