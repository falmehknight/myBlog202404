package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName ArticleDTO
 * @Description 文章DTO
 * @Author 谭颍豪
 * @Date 2024/5/10 22:20
 * @Version 1.0
 **/
@Data
@ApiModel(description = "文章DTO")
public class ArticleDTO {
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
    @NotBlank(message = "文章标题不能为空")
    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    /**
     * 文章内容
     */
    @NotBlank(message = "文章内容不能为空")
    @ApiModelProperty(value = "文章内容")
    private String articleContent;

    /**
     * 文章类型 (1 原创 2 转载 3 翻译)
     */
    @ApiModelProperty(value = "文章类型 (1原创 2转载 3翻译)")
    private Integer articleType;

    /**
     * 分类名
     */
    @NotBlank(message = "文章分类不能为空")
    @ApiModelProperty(value = "分类名")
    private String categoryName;

    /**
     * 标签名
     */
    @ApiModelProperty(value = "标签名")
    private List<String> tagNameList;

    /**
     * 是否置顶 (0否 1是)
     */
    @ApiModelProperty(value = "是否置顶 (0否 1是)")
    private Integer isTop;

    /**
     * 是否推荐 (0否 1是)
     */
    @ApiModelProperty(value = "是否推荐 (0否 1是)")
    private Integer isRecommend;

    /**
     * 状态 (1 公开 2 私密 3 草稿)
     */
    @ApiModelProperty(value = "状态 (1公开 2私密 3草稿)")
    private Integer status;
}
