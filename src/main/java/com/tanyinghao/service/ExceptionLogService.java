package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.ExceptionLog;
import com.tanyinghao.model.vo.PageResult;

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

    /**
     *
     * @Author TanYingHao
     * @Description 查看异常日志列表
     * @Date 18:06 2024/6/6
     * @Param [conditionDTO] 条件
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.entity.ExceptionLog> 日志列表
     **/
    PageResult<ExceptionLog> listExceptionLog(ConditionDTO conditionDTO);
}
