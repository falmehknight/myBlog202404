package com.tanyinghao.manager.factory;

import com.tanyinghao.comm.utils.SpringUtils;
import com.tanyinghao.model.entity.OperationLog;
import com.tanyinghao.service.OperationLogService;

import java.util.TimerTask;

/**
 * @ClassName AsyncFactory
 * @Description 异步工厂，产生任务用
 * @Author 谭颍豪
 * @Date 2024/5/13 22:55
 * @Version 1.0
 **/
public class AsyncFactory {

    /**
     *
     * @Author TanYingHao
     * @Description 记录操作日志
     * @Date 23:07 2024/5/13
     * @Param [operationLog] 操作日志信息
     * @return java.util.TimerTask 任务task
     **/
    public static TimerTask recordOperation(OperationLog operationLog) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(OperationLogService.class).saveOperationLog(operationLog);
            }
        };
    }
}
