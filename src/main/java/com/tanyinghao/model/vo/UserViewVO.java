package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UserViewVO
 * @Description 用户浏览
 * @Author 谭颍豪
 * @Date 2024/6/15 18:44
 * @Version 1.0
 **/
@Data
@ApiModel(description = "用户浏览")
public class UserViewVO {
    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private String date;

    /**
     * pv
     */
    @ApiModelProperty(value = "pv")
    private Integer pv;

    /**
     * uv
     */
    @ApiModelProperty(value = "uv")
    private Integer uv;
}
