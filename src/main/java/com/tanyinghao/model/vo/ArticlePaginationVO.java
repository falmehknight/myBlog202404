package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ArticlePaginationVO
 * @Description 文章上下篇
 * @Author 谭颍豪
 * @Date 2024/6/11 15:59
 * @Version 1.0
 **/
@Data
@ApiModel(description = "文章上下篇")
public class ArticlePaginationVO {
    /**
     * 文章id
     */
    @ApiModelProperty(value = "文章id")
    private Integer id;

    /**
     * 文章缩略图
     */
    @ApiModelProperty(value = "文章缩略图")
    private String articleCover;

    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    private String articleTitle;
}
