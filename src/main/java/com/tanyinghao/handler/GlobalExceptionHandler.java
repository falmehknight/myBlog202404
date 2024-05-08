package com.tanyinghao.handler;

import cn.dev33.satoken.exception.DisableServiceException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.tanyinghao.comm.exception.ServiceException;
import com.tanyinghao.comm.result.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static com.tanyinghao.comm.enums.StatusCodeEnum.*;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理类
 * @Author 谭颍豪
 * @Date 2024/5/8 22:57
 * @Version 1.0
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     *
     * @Author TanYingHao
     * @Description 处理业务异常
     * @Date 23:13 2024/5/8
     * @Param [e]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ExceptionHandler(value = ServiceException.class)
    public Result<?> handleServiceException(ServiceException e) {
        return Result.fail(e.getMessage());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 处理Assert异常
     * @Date 23:17 2024/5/8
     * @Param [e]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return Result.fail(e.getMessage());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 处理参数校验异常
     * @Date 23:17 2024/5/8
     * @Param [e]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return Result.fail(VALID_ERROR.getCode(), Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 处理权限不足
     * @Date 23:21 2024/5/8
     * @Param []
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ExceptionHandler(value = NotPermissionException.class)
    public Result<?> handleNotPermissionException() {
        return Result.fail("权限不足");
    }

    /**
     *
     * @Author TanYingHao
     * @Description 处理账号封禁
     * @Date 23:22 2024/5/8
     * @Param []
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ExceptionHandler(value = DisableServiceException.class)
    public Result<?> handleDisableServiceException() {
        return Result.fail("账号已被封禁");
    }

    /**
     *
     * @Author TanYingHao
     * @Description 处理无此角色异常
     * @Date 23:23 2024/5/8
     * @Param []
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ExceptionHandler(value = NotRoleException.class)
    public Result<?> handleNotRoleException() {
        return Result.fail("权限不足");
    }

    /**
     *
     * @Author TanYingHao
     * @Description 处理saToken异常
     * @Date 23:25 2024/5/8
     * @Param [nle]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ExceptionHandler(value = NotLoginException.class)
    public Result<?> handleNotLoginException(NotLoginException nle) {
        String message;
        if (nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供Token";
        } else if (nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token无效";
        } else if (nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token已过期";
        } else {
            message = "当前会话未登录";
        }
        return Result.fail(UNAUTHORIZED.getCode(), message);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 处理系统异常
     * @Date 23:28 2024/5/8
     * @Param []
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ExceptionHandler(value = Exception.class)
    public Result<?> handleSystemException() {
        return Result.fail(SYSTEM_ERROR.getCode(), SYSTEM_ERROR.getMsg());
    }
}
