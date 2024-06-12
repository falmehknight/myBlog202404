package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ArticleConditionList
 * @Description 文章条件列表VO
 * @Author 谭颍豪
 * @Date 2024/6/12 23:42
 * @Version 1.0
 **/
@Data
@Builder
@ApiModel(description = "文章条件列表VO")
public class ArticleConditionList {
    /**
     * 文章列表
     */
    @ApiModelProperty(value = "文章列表")
    private List<ArticleConditionVO> articleConditionVOList;

    /**
     * 条件名
     */
    @ApiModelProperty(value = "条件名")
    private String name;
}
