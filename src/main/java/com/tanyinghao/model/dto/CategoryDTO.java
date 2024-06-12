package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName CategoryDTO
 * @Description 分类DTO
 * @Author 谭颍豪
 * @Date 2024/6/13 0:03
 * @Version 1.0
 **/
@Data
@ApiModel(description = "分类DTO")
public class CategoryDTO {
    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private Integer id;

    /**
     * 分类名
     */
    @NotBlank(message = "分类名不能为空")
    @ApiModelProperty(value = "分类名")
    private String categoryName;
}
