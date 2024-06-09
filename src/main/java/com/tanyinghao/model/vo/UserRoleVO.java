package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UserRoleVO
 * @Description 用户角色VO
 * @Author 谭颍豪
 * @Date 2024/6/9 14:39
 * @Version 1.0
 **/
@Data
@ApiModel("用户角色VO")
public class UserRoleVO {
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private String id;

    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名")
    private String roleName;
}
