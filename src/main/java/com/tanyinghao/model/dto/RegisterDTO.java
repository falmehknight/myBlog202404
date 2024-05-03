package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @ClassName RegisterDTO
 * @Description 注册对应实体类
 * @Author 谭颍豪
 * @Date 2024/5/3 18:36
 * @Version 1.0
 **/
@Data
@ApiModel(description = "用户注册信息")
public class RegisterDTO {

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不对")
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码不能小于6位")
    @ApiModelProperty(value = "密码")
    private String password;

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码")
    private String code;
}
