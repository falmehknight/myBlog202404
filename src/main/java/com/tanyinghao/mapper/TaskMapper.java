package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.Task;
import com.tanyinghao.model.vo.TaskBackVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskMapper extends BaseMapper<Task> {
    
    /**
     *
     * @Author TanYingHao
     * @Description 查询定时任务数量
     * @Date 16:53 2024/6/13
     * @Param [condition]
     * @return java.lang.Long
     **/
    Long countTaskBackVO(@Param("condition") ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 分页查询定时任务列表
     * @Date 17:05 2024/6/13
     * @Param [limit, size, condition] 页码，大小，条件
     * @return java.util.List<com.tanyinghao.model.vo.TaskBackVO>
     **/
    List<TaskBackVO> selectTaskBackVO(Long limit, Long size, ConditionDTO condition);
}
