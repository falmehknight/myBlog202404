package com.tanyinghao.quartz.task;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.tanyinghao.mapper.VisitLogMapper;
import com.tanyinghao.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.tanyinghao.comm.constant.RedisConstant.UNIQUE_VISITOR;

/**
 * @ClassName TimeTask
 * @Description 执行定时任务
 * @Author 谭颍豪
 * @Date 2024/6/13 23:42
 * @Version 1.0
 **/
@SuppressWarnings(value = "all")
@Component("timeTask")
public class TimeTask {

    @Autowired
    private RedisService redisService;

    @Autowired
    private VisitLogMapper visitLogMapper;

    /**
     *
     * @Author TanYingHao
     * @Description 清除博客访问记录
     * @Date 23:42 2024/6/13
     * @Param []
     * @return void
     **/
    public void clear() {
        redisService.deleteObject(UNIQUE_VISITOR);
    }

    public void test() {
        System.out.println("测试任务");
    }

    /**
     *
     * @Author TanYingHao
     * @Description 删除一周前的访问日志
     * @Date 23:46 2024/6/13
     * @Param []
     * @return void
     **/
    public void clearVistiLog() {
        DateTime endTime = DateUtil.beginOfDay(DateUtil.offsetDay(new Date(), -7));
        visitLogMapper.deleteVisitLog(endTime);
    }
}
