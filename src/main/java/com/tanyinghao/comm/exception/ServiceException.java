package com.tanyinghao.comm.exception;

import lombok.Getter;
import static com.tanyinghao.comm.enums.StatusCodeEnum.FAIL;

/**
 * @ClassName ServiceException
 * @Description 业务异常
 * @Author 谭颍豪
 * @Date 2024/5/8 19:20
 * @Version 1.0
 **/
@Getter
public final class ServiceException extends RuntimeException {
    /**
     * 返回失败状态码
     **/
    private Integer code = FAIL.getCode();

    /**
     * 返回信息
     **/
    private final String message;

    public ServiceException(String message) {
        this.message = message;
    }
}
