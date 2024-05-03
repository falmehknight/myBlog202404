package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName CodeDTO
 * @Description 第三方code信息
 * @Author 谭颍豪
 * @Date 2024/5/3 18:43
 * @Version 1.0
 **/
@Data
@ApiModel(description = "Code信息")
public class CodeDTO {

    @NotBlank(message = "code不能为空")
    @ApiModelProperty(value = "code")
    private String Code;
}
