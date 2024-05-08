package com.tanyinghao.controller.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.tanyinghao.comm.annotation.AccessLimit;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.CodeDTO;
import com.tanyinghao.model.dto.LoginDTO;
import com.tanyinghao.model.dto.RegisterDTO;
import com.tanyinghao.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private LoginService loginService;

    /**
     *
     * @Author TanYingHao
     * @Description 用户登录
     * @Date 16:19 2024/5/3
     * @Param [loginDTO] 登录参数
     * @return com.tanyinghao.comm.result.Result<java.lang.String>  Token值
     **/
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result<String> login(@Validated @RequestBody LoginDTO loginDTO) {

        return Result.success(loginService.login(loginDTO));
    }
    
    /**
     *
     * @Author TanYingHao
     * @Description 用户退出
     * @Date 16:23 2024/5/3
     **/
    @SaCheckLogin
    @ApiOperation(value = "用户退出")
    @GetMapping("/logout")
    public Result<?> logout() {
        StpUtil.logout();
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 发送邮箱验证码，用Redis实现限流60s最多发送一次
     * @Date 16:38 2024/5/3
     * @Param [username] 用户名
     **/
    @AccessLimit(seconds = 60, maxCount = 1)
    @ApiOperation(value = "发送邮箱验证码")
    @GetMapping("/code")
    public Result<?> sendCode(String username) {
        loginService.sendCode(username);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 用户邮箱注册
     * @Date 18:41 2024/5/3
     * @Param [registerDTO] 注册信息
     **/
    @ApiOperation(value = "用户邮箱注册")
    @PostMapping("/register")
    public Result<?> register(@Validated @RequestBody RegisterDTO registerDTO) {
        loginService.register(registerDTO);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description GitEE登录
     * @Date 18:45 2024/5/3
     * @Param [codeDTO] 第三方code
     **/
    @ApiOperation(value = "Gitee登录")
    @PostMapping("/oauth/gitee")
    public Result<String> giteeLogin(@Validated @RequestBody CodeDTO codeDTO) {
        return Result.success(loginService.giteeLogin(codeDTO));
    }

    /**
     *
     * @Author TanYingHao
     * @Description github登录
     * @Date 18:47 2024/5/3
     * @Param [codeDTO] 第三方登录
     **/
    @ApiOperation(value = "Github登录")
    @PostMapping("/oauth/github")
    public Result<String> githubLogin(@Validated @RequestBody CodeDTO codeDTO) {
        return Result.success(loginService.githubLogin(codeDTO));
    }

    /**
     *
     * @Author TanYingHao
     * @Description QQ登录
     * @Date 18:47 2024/5/3
     * @Param [codeDTO]
     **/
    @ApiOperation(value = "QQ登录")
    @PostMapping("/oauth/qq")
    public Result<String> qqLogin(@Validated @RequestBody CodeDTO codeDTO) {
        return Result.success(loginService.qqLogin(codeDTO));
    }
}
