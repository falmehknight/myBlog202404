package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName TalkDTO
 * @Description 说说DTO
 * @Author 谭颍豪
 * @Date 2024/6/14 10:03
 * @Version 1.0
 **/
@Data
@ApiModel(description = "说说DTO")
public class TalkDTO {
    /**
     * 说说id
     */
    @ApiModelProperty(value = "说说id")
    private Integer id;

    /**
     * 说说内容
     */
    @NotBlank(message = "说说内容不能为空")
    @ApiModelProperty(value = "说说内容")
    private String talkContent;

    /**
     * 说说图片
     */
    @ApiModelProperty(value = "说说图片")
    private String images;

    /**
     * 是否置顶 (0否 1是)
     */
    @NotNull(message = "置顶状态不能为空")
    @ApiModelProperty(value = "是否置顶 (0否 1是)")
    private Integer isTop;

    /**
     * 说说状态 (1公开 2私密)
     */
    @NotNull(message = "说说状态不能为空")
    @ApiModelProperty(value = "说说状态 (1公开 2私密)")
    private Integer status;
}
