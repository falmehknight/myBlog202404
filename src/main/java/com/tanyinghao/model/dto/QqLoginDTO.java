package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName QqLoginDTO
 * @Description QQ登录信息实体
 * @Author 谭颍豪
 * @Date 2024/5/8 22:45
 * @Version 1.0
 **/
@Data
@ApiModel(description = "QQ登录DTO")
public class QqLoginDTO {

    @ApiModelProperty(value = "openId")
    private String openid;

    @ApiModelProperty(value = "clientId")
    private String client_id;
}
