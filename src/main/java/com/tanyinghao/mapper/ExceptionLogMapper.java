package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.ExceptionLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExceptionLogMapper extends BaseMapper<ExceptionLog> {

    /**
     *
     * @Author TanYingHao
     * @Description 查询异常日志
     * @Date 18:17 2024/6/6
     * @Param [limit, size, conditionDTO] 页码 大小 条件
     * @return java.util.List<com.tanyinghao.model.entity.ExceptionLog>
     **/
    List<ExceptionLog> selectExceptionLogList(@Param("limit") Long limit, @Param("size") Long size, @Param("condition") ConditionDTO conditionDTO);
}
