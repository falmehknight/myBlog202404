package com.tanyinghao.controller.user;

import com.tanyinghao.comm.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description 登录模块,登录控制器
 * @Author 谭颍豪
 * @Date 2024/4/29 22:48
 * @Version 1.0
 **/
@Api("登录模块")
@RestController
public class LoginController {

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result<String> login() {
        return Result.success();
    }
}
