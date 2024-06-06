package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.OperationLog;
import com.tanyinghao.model.vo.OperationLogVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationLogMapper extends BaseMapper<OperationLog> {

    /**
     *
     * @Author TanYingHao
     * @Description 查询操作日志
     * @Date 17:53 2024/6/6
     * @Param [limit, size, conditionDTO] 页码，大小，条件
     * @return java.util.List<com.tanyinghao.model.vo.OperationLogVO>
     **/
    List<OperationLogVO> selectOperationLogVOList(@Param("limit") Long limit, @Param("size") Long size, @Param("condition") ConditionDTO conditionDTO);
}
