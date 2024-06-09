package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName MetaVO
 * @Description 其他信息
 * @Author 谭颍豪
 * @Date 2024/6/9 14:46
 * @Version 1.0
 **/
@Data
@Builder
@ApiModel(description = "其他信息")
public class MetaVO {
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String title;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    /**
     * 是否隐藏
     */
    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidden;

}
