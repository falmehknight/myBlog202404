package com.tanyinghao.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName TalkBackInfoVO
 * @Description 说说信息VO
 * @Author 谭颍豪
 * @Date 2024/6/14 10:07
 * @Version 1.0
 **/
@Data
@ApiModel(description = "说说信息VO")
public class TalkBackInfoVO {
    /**
     * 说说id
     */
    @ApiModelProperty(value = "说说id")
    private Integer id;

    /**
     * 说说内容
     */
    @ApiModelProperty(value = "说说内容")
    private String talkContent;

    /**
     * 图片
     */
    @JsonIgnore
    @ApiModelProperty(value = "图片")
    private String images;

    /**
     * 图片列表
     */
    @ApiModelProperty(value = "图片列表")
    private List<String> imgList;

    /**
     * 是否置顶 (0否 1是)
     */
    @ApiModelProperty(value = "是否置顶 (0否 1是)")
    private Integer isTop;

    /**
     * 说说状态 (1公开 2私密)
     */
    @ApiModelProperty(value = "说说状态 (1公开 2私密)")
    private Integer status;

}