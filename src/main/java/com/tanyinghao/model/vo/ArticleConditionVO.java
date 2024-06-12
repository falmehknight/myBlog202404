package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName ArticleConditionVO
 * @Description 文章条件VO
 * @Author 谭颍豪
 * @Date 2024/6/12 23:43
 * @Version 1.0
 **/
@Data
@ApiModel(description = "文章条件VO")
public class ArticleConditionVO {
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

    /**
     * 文章分类
     */
    @ApiModelProperty(value = "文章分类")
    private CategoryOptionVO category;

    /**
     * 文章标签
     */
    @ApiModelProperty(value = "文章标签")
    private List<TagOptionVO> tagVOList;

    /**
     * 发表时间
     */
    @ApiModelProperty(value = "发表时间")
    private LocalDateTime createTime;
}
