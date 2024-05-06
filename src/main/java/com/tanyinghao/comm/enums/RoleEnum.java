package com.tanyinghao.comm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @Author TanYingHao
 * @Description 角色枚举
 * @Date 23:27 2024/5/6
 **/
@Getter
@AllArgsConstructor
public enum RoleEnum {

    /**
     * 管理员
     */
    ADMIN("1", "admin"),
    /**
     * 用户
     */
    USER("2", "user"),
    /**
     * 测试账号
     */
    TEST("3", "test");

    /**
     * 角色id
     */
    private final String roleId;

    /**
     * 描述
     */
    private final String name;
}
