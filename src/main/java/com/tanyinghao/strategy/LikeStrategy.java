package com.tanyinghao.strategy;

public interface LikeStrategy {
    /**
     * 点赞
     *
     * @param typeId 类型id
     */
    void like(Integer typeId);
}
