package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName AlbumBackVO
 * @Description 相册后台VO
 * @Author 谭颍豪
 * @Date 2024/6/15 13:50
 * @Version 1.0
 **/
@Data
@ApiModel(description = "相册后台VO")
public class AlbumBackVO {
    /**
     * 相册id
     */
    @ApiModelProperty(value = "相册id")
    private Integer id;

    /**
     * 相册名
     */
    @ApiModelProperty(value = "相册名")
    private String albumName;

    /**
     * 相册封面
     */
    @ApiModelProperty(value = "相册封面")
    private String albumCover;

    /**
     * 相册描述
     */
    @ApiModelProperty(value = "相册描述")
    private String albumDesc;

    /**
     * 照片数量
     */
    @ApiModelProperty(value = "照片数量")
    private Long photoCount;

    /**
     * 状态 (1公开 2私密)
     */
    @ApiModelProperty(value = "状态 (1公开 2私密)")
    private Integer status;
}
