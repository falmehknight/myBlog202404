package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @ClassName LoginDto
 * @Description 登录信息
 * @Author 谭颍豪
 * @Date 2024/5/1 18:18
 * @Version 1.0
 **/
@Data
@ApiModel(description = "登录信息")
public class LoginDTO {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String userName;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码不能小于6位")
    @ApiModelProperty(value = "密码")
    private String passWord;
}
