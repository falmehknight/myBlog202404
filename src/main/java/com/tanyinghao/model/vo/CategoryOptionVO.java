package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CategoryOptionVO
 * @Description 分类选项VO
 * @Author 谭颍豪
 * @Date 2024/6/11 15:59
 * @Version 1.0
 **/
@Data
@ApiModel(description = "分类选项VO")
public class CategoryOptionVO {
    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private Integer id;

    /**
     * 分类名
     */
    @ApiModelProperty(value = "分类名")
    private String categoryName;
}
