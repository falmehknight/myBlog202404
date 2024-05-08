package com.tanyinghao.model.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName SocialUserInfoVO
 * @Description 第三方账号信息
 * @Author 谭颍豪
 * @Date 2024/5/7 23:12
 * @Version 1.0
 **/
@Data
@Builder
@ApiModel(description = "第三方账号信息")
public class SocialUserInfoVO {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;
}
