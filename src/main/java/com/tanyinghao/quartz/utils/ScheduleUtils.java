package com.tanyinghao.quartz.utils;

import com.tanyinghao.comm.constant.ScheduleConstant;
import com.tanyinghao.comm.enums.TaskStatusEnum;
import com.tanyinghao.comm.exception.ServiceException;
import com.tanyinghao.model.entity.Task;
import com.tanyinghao.quartz.execution.QuartzDisallowConcurrentExecution;
import com.tanyinghao.quartz.execution.QuartzJobExecution;
import org.quartz.*;

import static com.tanyinghao.comm.constant.CommConstant.TRUE;

/**
 * @ClassName ScheduleUtils
 * @Description 定时任务工具类
 * @Author 谭颍豪
 * @Date 2024/6/13 17:23
 * @Version 1.0
 **/
public class ScheduleUtils {

    /**
     *
     * @Author TanYingHao
     * @Description 得到quartz任务类
     * @Date 17:30 2024/6/13
     * @Param [task] 执行计划
     * @return java.lang.Class<? extends org.quartz.Job> 具体的任务类（并发和非并发）
     **/
    private static Class<? extends Job> getQuartzJobClass(Task task) {
        boolean isConcurrent = TRUE.equals(task.getConcurrent());
        return isConcurrent ? QuartzJobExecution.class : QuartzDisallowConcurrentExecution.class;
    }

    /**
     *
     * @Author TanYingHao
     * @Description 创建定时任务
     * @Date 23:17 2024/6/13
     * @Param [scheduler, task] 调度器，任务
     **/
    public static void createScheduleJob(Scheduler scheduler, Task task) {
        try {
            Class<? extends Job> jobClass = getQuartzJobClass(task);
            // 构建task信息
            Integer taskId = task.getId();
            String taskGroup = task.getTaskGroup();
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(getJobKey(taskId, taskGroup)).build();
            // 表达式调度构建器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCronExpression());
            cronScheduleBuilder = handleCronScheduleMisfirePolicy(task, cronScheduleBuilder);
            // 按新的cron表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(taskId, taskGroup))
                    .withSchedule(cronScheduleBuilder).build();
            // 放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(ScheduleConstant.TASK_PROPERTIES, task);
            // 判断是否存在
            if (scheduler.checkExists(getJobKey(taskId, taskGroup))) {
                // 防止创建时存在数据问题，先移除，然后执行创建操作
                scheduler.deleteJob(getJobKey(taskId, taskGroup));
            }
            scheduler.scheduleJob(jobDetail, trigger);
            if (task.getStatus().equals(TaskStatusEnum.PAUSE.getStatus())) {
                scheduler.pauseJob(getJobKey(taskId, taskGroup));
            }
        } catch (ServiceException | SchedulerException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    /**
     *
     * @Author TanYingHao
     * @Description 构建任务触发对象
     * @Date 23:30 2024/6/13
     * @Param [taskId, taskGroup]
     * @return org.quartz.TriggerKey
     **/
    private static TriggerKey getTriggerKey(Integer taskId, String taskGroup) {
        return TriggerKey.triggerKey(ScheduleConstant.TASK_CLASS_NAME + taskId, taskGroup);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 设置定时任务策略
     * @Date 23:23 2024/6/13
     * @Param [task, cronScheduleBuilder]
     * @return org.quartz.CronScheduleBuilder
     **/
    private static CronScheduleBuilder handleCronScheduleMisfirePolicy(Task task, CronScheduleBuilder cronScheduleBuilder) throws ServiceException {
        switch (task.getMisfirePolicy()) {
            case ScheduleConstant.MISFIRE_DEFAULT:
                return cronScheduleBuilder;
            case ScheduleConstant.MISFIRE_IGNORE_MISFIRES:
                return cronScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
            case ScheduleConstant.MISFIRE_FIRE_AND_PROCEED:
                return cronScheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
            case ScheduleConstant.MISFIRE_DO_NOTHING:
                return cronScheduleBuilder.withMisfireHandlingInstructionDoNothing();
            default:
                throw new ServiceException("The task misfire policy '" + task.getMisfirePolicy()
                        + "' cannot be used in cron schedule tasks");
        }
    }

    /**
     *
     * @Author TanYingHao
     * @Description 构建任务键对象
     * @Date 23:20 2024/6/13
     * @Param [taskId, taskGroup]
     * @return java.lang.String
     **/
    private static JobKey getJobKey(Integer taskId, String taskGroup) {
        return JobKey.jobKey(ScheduleConstant.TASK_CLASS_NAME + taskId, taskGroup);
    }

}
