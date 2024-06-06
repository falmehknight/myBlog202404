package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.entity.ExceptionLog;

public interface ExceptionLogService extends IService<ExceptionLog> {

    /**
     *
     * @Author TanYingHao
     * @Description 保存异常日志
     * @Date 16:23 2024/6/6
     * @Param [exceptionLog]
     * @return void
     **/
    void saveExceptionLog(ExceptionLog exceptionLog);
}
