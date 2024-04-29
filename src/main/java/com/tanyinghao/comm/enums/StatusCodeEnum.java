package com.tanyinghao.comm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName StatusCodeEnum
 * @Description 状态码枚举
 * @Author 谭颍豪
 * @Date 2024/4/29 23:20
 * @Version 1.0
 **/
@Getter
@AllArgsConstructor
public enum StatusCodeEnum {

    SUCCESS(200, "操作成功"),

    VALID_ERROR(400, "参数错误"),

    UNAUTHORIZED(402, "未登录"),

    SYSTEM_ERROR(-1, "系统异常"),

    FAIL(500, "操作失败");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 返回信息
     */
    private final String msg;

}
