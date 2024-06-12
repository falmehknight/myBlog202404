package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName ArticleRecommendVO
 * @Description 推荐文章
 * @Author 谭颍豪
 * @Date 2024/6/12 16:17
 * @Version 1.0
 **/
@Data
@ApiModel(description = "推荐文章")
public class ArticleRecommendVO {
    /**
     * 文章id
     * */
    @ApiModelProperty(value = "文章id")
    private Integer id;

    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    /**
     * 文章缩略图
     */
    @ApiModelProperty(value = "文章缩略图")
    private String articleCover;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    private LocalDateTime createTime;

}
