package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @ClassName PasswordDTO
 * @Description 密码DTO
 * @Author 谭颍豪
 * @Date 2024/6/9 15:19
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户密码")
public class PasswordDTO {
    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码不能为空")
    @ApiModelProperty(value = "旧密码")
    private String oldPassword;

    /**
     * 新密码
     */
    @Size(min = 6, message = "新密码不能少于6位")
    @NotBlank(message = "新密码不能为空")
    @ApiModelProperty(value = "新密码")
    private String newPassword;
}
