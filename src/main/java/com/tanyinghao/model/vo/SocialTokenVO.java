package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName SocialTokenVO
 * @Description 第三方token
 * @Author 谭颍豪
 * @Date 2024/5/7 23:12
 * @Version 1.0
 **/
@Data
@Builder
@ApiModel(description = "第三方token")
public class SocialTokenVO {

    /**
     * 开放id
     */
    @ApiModelProperty(value = "开放id")
    private String openId;

    /**
     * 访问令牌
     */
    @ApiModelProperty(value = "访问令牌")
    private String accessToken;

    /**
     * 登录类型
     */
    @ApiModelProperty(value = "登录类型")
    private Integer loginType;
}
