package com.tanyinghao.model.vo;

import lombok.Data;

/**
 * @ClassName QqUserInfoVO
 * @Description qq用户信息
 * @Author 谭颍豪
 * @Date 2024/5/8 22:52
 * @Version 1.0
 **/
@Data
public class QqUserInfoVO {

    /**
     * 用户开放id
     */
    private String openId;

    /**
     * QQ头像
     */
    private String figureurl_qq_1;

    /**
     * 昵称
     */
    private String nickname;
}
