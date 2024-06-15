package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName ArticleRankVO
 * @Description 文章浏览量排行
 * @Author 谭颍豪
 * @Date 2024/6/15 18:43
 * @Version 1.0
 **/
@Data
@Builder
@ApiModel(description = "文章浏览量排行")
public class ArticleRankVO {

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String articleTitle;

    /**
     * 浏览量
     */
    @ApiModelProperty(value = "浏览量")
    private Integer viewCount;
}
