package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName GitUserInfoVO
 * @Description git用户信息
 * @Author 谭颍豪
 * @Date 2024/5/8 19:35
 * @Version 1.0
 **/
@Data
@ApiModel(description = "Git用户信息")
public class GitUserInfoVO {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private String id;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar_url;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String name;

    /**
     * 登录
     */
    @ApiModelProperty(value = "登录")
    private String login;
}
