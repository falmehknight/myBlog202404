package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName MessageDTO
 * @Description 留言DTO
 * @Author 谭颍豪
 * @Date 2024/6/15 15:58
 * @Version 1.0
 **/
@Data
@ApiModel(description = "留言DTO")
public class MessageDTO {
    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 头像
     */
    @NotBlank(message = "头像不能为空")
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 留言内容
     */
    @NotBlank(message = "留言内容不能为空")
    @ApiModelProperty(value = "留言内容")
    private String messageContent;
}
