package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.OperationLog;
import com.tanyinghao.model.vo.OperationLogVO;
import com.tanyinghao.model.vo.PageResult;

/**
 *
 * @Author TanYingHao
 * @Description 操作日志业务接口
 * @Date 22:58 2024/5/13
 **/

public interface OperationLogService extends IService<OperationLog> {

    /**
     *
     * @Author TanYingHao
     * @Description 查看操作日志列表
     * @Date 23:04 2024/5/13
     * @Param [condition] 条件
     * @return PageResult<OperationLogVO> 日志列表
     **/
    PageResult<OperationLogVO> listOperationLogVO(ConditionDTO condition);


    /**
     *
     * @Author TanYingHao
     * @Description 保存操作日志
     * @Date 23:07 2024/5/13
     * @Param [operationLog] 操作日志信息
     **/
    void saveOperationLog(OperationLog operationLog);
}
