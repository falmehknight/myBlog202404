package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName StatusDTO
 * @Description 状态DTO
 * @Author 谭颍豪
 * @Date 2024/6/13 12:24
 * @Version 1.0
 **/
@Data
@ApiModel(description = "状态DTO")
public class StatusDTO {
    /**
     * id
     */
    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = "状态")
    private Integer status;
}
