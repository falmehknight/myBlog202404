package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CategoryVO
 * @Description 分类列表
 * @Author 谭颍豪
 * @Date 2024/6/13 0:01
 * @Version 1.0
 **/
@Data
@ApiModel(description = "分类列表")
public class CategoryVO {
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

    /**
     * 文章数量
     */
    @ApiModelProperty(value = "文章数量")
    private Integer articleCount;
}
