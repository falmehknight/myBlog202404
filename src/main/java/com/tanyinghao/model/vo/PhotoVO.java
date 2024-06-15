package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName PhotoVO
 * @Description 照片VO
 * @Author 谭颍豪
 * @Date 2024/6/15 13:48
 * @Version 1.0
 **/
@ApiModel(description = "照片VO")
public class PhotoVO {
    /**
     * 照片id
     */
    @ApiModelProperty(value = "照片id")
    private Integer id;

    /**
     * 照片链接
     */
    @ApiModelProperty(value = "照片链接")
    private String photoUrl;
}
