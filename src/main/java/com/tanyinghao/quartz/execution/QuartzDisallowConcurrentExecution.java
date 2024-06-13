package com.tanyinghao.quartz.execution;

import com.tanyinghao.model.entity.Task;
import com.tanyinghao.quartz.utils.TaskInvokeUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * @ClassName QuartzDisallowConcurrentExecution
 * @Description 定时任务处理（禁止并发）
 * @Author 谭颍豪
 * @Date 2024/6/13 23:06
 * @Version 1.0
 **/
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob{
    @Override
    protected void doExecute(JobExecutionContext context, Task task) throws Exception {
        TaskInvokeUtils.invokeMethod(task);
    }
}
