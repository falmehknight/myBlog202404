package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ReplyCountVO
 * @Description 回复数VO
 * @Author 谭颍豪
 * @Date 2024/6/15 0:40
 * @Version 1.0
 **/
@Data
@ApiModel(description = "回复数VO")
public class ReplyCountVO {
    /**
     * 评论id
     */
    @ApiModelProperty(value = "评论id")
    private Integer commentId;

    /**
     * 回复数
     */
    @ApiModelProperty(value = "回复数")
    private Integer replyCount;
}
