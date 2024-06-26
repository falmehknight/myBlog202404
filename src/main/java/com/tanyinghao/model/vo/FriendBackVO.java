package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName FriendBackVO
 * @Description 友链后台VO
 * @Author 谭颍豪
 * @Date 2024/6/15 18:17
 * @Version 1.0
 **/
@Data
@ApiModel(description = "友链后台VO")
public class FriendBackVO {
    /**
     * 友链id
     */
    @ApiModelProperty(value = "友链id")
    private Integer id;

    /**
     * 友链颜色
     */
    @ApiModelProperty(value = "友链颜色")
    private String color;

    /**
     * 友链名称
     */
    @ApiModelProperty(value = "友链名称")
    private String name;

    /**
     * 友链头像
     */
    @ApiModelProperty(value = "友链头像")
    private String avatar;

    /**
     * 友链地址
     */
    @ApiModelProperty(value = "友链地址")
    private String url;

    /**
     * 友链介绍
     */
    @ApiModelProperty(value = "友链介绍")
    private String introduction;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
