package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.entity.VisitLog;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitLogMapper extends BaseMapper<VisitLog> {


    /**
     *
     * @Author TanYingHao
     * @Description 查询访问日志
     * @Date 14:50 2024/6/6
     * @Param [limit, size, keyword] 从某个位置开始查询，查询的大小，关键字
     * @return java.util.List<com.tanyinghao.model.entity.VisitLog> 访问日志列表
     **/
    List<VisitLog> selectVisitLogList(@Param("limit") Long limit, @Param("size") Long size, @Param("keyword") String keyword);
}
