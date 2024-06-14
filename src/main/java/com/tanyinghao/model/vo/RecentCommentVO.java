package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName RecentCommentVO
 * @Description 最新评论
 * @Author 谭颍豪
 * @Date 2024/6/14 16:28
 * @Version 1.0
 **/
@Data
@ApiModel(description = "最新评论")
public class RecentCommentVO {
    /**
     * 评论id
     */
    @ApiModelProperty(value = "评论id")
    private Integer id;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户昵称")
    private String avatar;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String commentContent;

    /**
     * 评论时间
     */
    @ApiModelProperty(value = "评论时间")
    private LocalDateTime createTime;
}
