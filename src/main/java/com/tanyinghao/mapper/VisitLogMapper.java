package com.tanyinghao.mapper;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.entity.VisitLog;


import com.tanyinghao.model.vo.UserViewVO;
import org.apache.ibatis.annotations.Delete;
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

    /**
     *
     * @Author TanYingHao
     * @Description 删除一周前的访问日志
     * @Date 23:46 2024/6/13
     * @Param [endTime] 结束时间
     **/
    @Delete("DELETE FROM t_visit_log where create_time <= #{endTime}")
    void deleteVisitLog(@Param("endTime") DateTime endTime);

    /**
     *
     * @Author TanYingHao
     * @Description 查看在指定时间间隔类的用户浏览
     * @Date 18:50 2024/6/15
     * @Param [startTime, endTime]
     * @return java.util.List<com.tanyinghao.model.vo.UserViewVO>
     **/
    List<UserViewVO> selectUserViewList(@Param("startTime") DateTime startTime, @Param("endTime") DateTime endTime);
}
