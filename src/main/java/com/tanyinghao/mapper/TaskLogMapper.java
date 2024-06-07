package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.TaskLog;
import com.tanyinghao.model.vo.TaskLogVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskLogMapper extends BaseMapper<TaskLog> {

    /**
     *
     * @Author TanYingHao
     * @Description 查询定时任务日志数量
     * @Date 22:59 2024/6/7
     * @Param [condition] 条件
     * @return java.lang.Long
     **/
    Long selectTaskLogCount(@Param("condition" ) ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 查询定时任务日志列表
     * @Date 23:06 2024/6/7
     * @Param [limit, size, condition]
     * @return java.util.List<com.tanyinghao.model.vo.TaskLogVO>
     **/
    List<TaskLogVO> selectTaskLogVOList(@Param("limit") Long limit, @Param("size") Long size, @Param("condition") ConditionDTO condition);
}
