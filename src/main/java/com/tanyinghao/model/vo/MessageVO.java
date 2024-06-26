package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName MessageVO
 * @Description 留言VO
 * @Author 谭颍豪
 * @Date 2024/6/15 15:56
 * @Version 1.0
 **/
@Data
@ApiModel(description = "留言VO")
public class MessageVO {
    /**
     * 留言id
     */
    @ApiModelProperty(value = "留言id")
    private Integer id;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 留言内容
     */
    @ApiModelProperty(value = "留言内容")
    private String messageContent;
}
