package com.tanyinghao.strategy.context;

import com.tanyinghao.comm.enums.LikeTypeEnum;
import com.tanyinghao.strategy.LikeStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName LikeStrategyContext
 * @Description 点赞策略上下文
 * @Author 谭颍豪
 * @Date 2024/6/14 10:55
 * @Version 1.0
 **/
@Service
public class LikeStrategyContext {

    @Autowired
    private Map<String, LikeStrategy> likeStrategyMap;

    /**
     *
     * @Author TanYingHao
     * @Description 点赞，类型id
     * @Date 11:05 2024/6/14
     * @Param [likeType, typeId] 点赞类型， 类型id
     **/
    public void executeLikeStrategy(LikeTypeEnum likeType, Integer typeId) {
        likeStrategyMap.get(likeType.getStrategy()).like(typeId);
    }
}
