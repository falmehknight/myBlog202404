package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.VisitLog;
import com.tanyinghao.model.vo.PageResult;

/**
 *
 * @Author TanYingHao
 * @Description 访问日志服务
 * @Date 19:40 2024/5/16
 **/
public interface VisitLogService extends IService<VisitLog> {
    
    /**
     *
     * @Author TanYingHao
     * @Description 保存访问日志 
     * @Date 19:41 2024/5/16
     * @Param [visitLog] 访问日志信息
     * @return void
     **/
    void saveVisitLog(VisitLog visitLog);
    
    
    /**
     *
     * @Author TanYingHao
     * @Description 查看访问日志列表
     * @Date 19:42 2024/5/16
     * @Param [condition]
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.entity.VisitLog>
     **/
    PageResult<VisitLog> listVisitLog(ConditionDTO condition);
}

