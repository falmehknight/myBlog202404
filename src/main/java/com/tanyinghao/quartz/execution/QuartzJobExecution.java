package com.tanyinghao.quartz.execution;

import com.tanyinghao.model.entity.Task;
import com.tanyinghao.quartz.utils.TaskInvokeUtils;
import org.quartz.JobExecutionContext;

/**
 * @ClassName QuartzJobExecution
 * @Description 定时任务处理（允许并发执行）
 * @Author 谭颍豪
 * @Date 2024/6/13 20:04
 * @Version 1.0
 **/
public class QuartzJobExecution extends AbstractQuartzJob{
    @Override
    protected void doExecute(JobExecutionContext context, Task task) throws Exception {
        TaskInvokeUtils.invokeMethod(task);
    }
}
