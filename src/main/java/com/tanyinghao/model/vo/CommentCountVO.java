package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CommentCountVO
 * @Description 评论数量VO
 * @Author 谭颍豪
 * @Date 2024/6/14 11:59
 * @Version 1.0
 **/
@Data
@ApiModel(description = "评论数量VO")
public class CommentCountVO {
    /**
     * 类型id
     */
    @ApiModelProperty(value = "类型id")
    private Integer id;

    /**
     * 评论数量
     */
    @ApiModelProperty(value = "评论数量")
    private Integer commentCount;
}
