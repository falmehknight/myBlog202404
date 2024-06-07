package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.TaskLog;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.TaskLogVO;

public interface TaskLogService extends IService<TaskLog> {

    /**
     *
     * @Author TanYingHao
     * @Description 查看后台定时任务日志
     * @Date 23:15 2024/6/7
     * @Param [condition]
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.TaskLogVO>
     **/
    PageResult<TaskLogVO> listTaskLog(ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 清空定时任务日志
     * @Date 23:16 2024/6/7
     * @Param []
     * @return void
     **/
    void clearTaskLog();
}
