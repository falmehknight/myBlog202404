package com.tanyinghao.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName MenuOption
 * @Description 菜单选项树
 * @Author 谭颍豪
 * @Date 2024/6/8 23:34
 * @Version 1.0
 **/
@Data
@ApiModel(description = "菜单选项树")
public class MenuOption {
    /**
     * id
     */
    @ApiModelProperty(value = "菜单id")
    private Integer value;

    /**
     * 父菜单id
     */
    @JsonIgnore
    @ApiModelProperty(value = "父菜单id")
    private Integer parentId;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String label;

    /**
     * 子菜单树
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(value = "子菜单树")
    private List<MenuOption> children;
}
