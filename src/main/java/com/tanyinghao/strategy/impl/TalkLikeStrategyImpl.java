package com.tanyinghao.strategy.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tanyinghao.mapper.TalkMapper;
import com.tanyinghao.model.entity.Talk;
import com.tanyinghao.service.RedisService;
import com.tanyinghao.strategy.LikeStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.tanyinghao.comm.constant.RedisConstant.TALK_LIKE_COUNT;
import static com.tanyinghao.comm.constant.RedisConstant.USER_TALK_LIKE;

/**
 * @ClassName TalkLikeStrategyImpl
 * @Description 说说点赞策略
 * @Author 谭颍豪
 * @Date 2024/6/14 11:10
 * @Version 1.0
 **/
@Service("talkLikeStrategyImpl")
public class TalkLikeStrategyImpl implements LikeStrategy{

    @Autowired
    private TalkMapper talkMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public void like(Integer talkId) {
        Talk talk = talkMapper.selectOne(new LambdaQueryWrapper<Talk>()
                .select(Talk::getId)
                .eq(Talk::getId, talkId));
        Assert.notNull(talk, "说说不存在");
        // 用户id作为键，说说id作为值，记录用户点赞记录
        String userLikeTalkKey = USER_TALK_LIKE + StpUtil.getLoginIdAsInt();
        // 判断是否点赞过
        if (redisService.hasSetValue(userLikeTalkKey, talkId)) {
            // 取消点赞，则删除用户id中的说说id
            redisService.deleteSet(userLikeTalkKey, talkId);
            // 说说点赞量减1
            redisService.decrHash(TALK_LIKE_COUNT, talkId.toString(), 1L);
        } else {
            // 点赞则在用户id记录说说id
            redisService.setSet(userLikeTalkKey, talkId);
            // 说说点赞量+1
            redisService.incrHash(TALK_LIKE_COUNT, talkId.toString(), 1L);
        }
    }
}
