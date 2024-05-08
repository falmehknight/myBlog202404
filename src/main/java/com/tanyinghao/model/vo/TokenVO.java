package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName TokenVO
 * @Description Token
 * @Author 谭颍豪
 * @Date 2024/5/7 23:41
 * @Version 1.0
 **/
@Data
@ApiModel(description = "Token")
public class TokenVO {

    /*/
        访问令牌
     */
    @ApiModelProperty(value = "访问令牌")
    private String access_token;
}
