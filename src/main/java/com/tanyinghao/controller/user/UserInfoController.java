package com.tanyinghao.controller.user;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.EmailDTO;
import com.tanyinghao.model.dto.UserDTO;
import com.tanyinghao.model.dto.UserInfoDTO;
import com.tanyinghao.model.vo.UserInfoVO;
import com.tanyinghao.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName UserInfoController
 * @Description 用户信息模块
 * @Author 谭颍豪
 * @Date 2024/6/10 22:19
 * @Version 1.0
 **/
@RestController
@Api("用户信息模块")
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserService userService;

    /**
     *
     * @Author TanYingHao
     * @Description 获取登录用户信息
     * @Date 22:24 2024/6/10
     * @Param []
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.UserInfoVO>
     **/
    @SaCheckLogin
    @ApiOperation("获取登录用户信息")
    @GetMapping("/getUserInfo")
    public Result<UserInfoVO> getUserInfo() {
        return Result.success(userService.getUserInfo());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 修改用户邮箱
     * @Date 22:28 2024/6/10
     * @Param [email]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ApiOperation("修改用户邮箱")
    @SaCheckPermission("user:email:update")
    @PutMapping("/email")
    public Result<?> updateUserEmail(@Validated @RequestBody EmailDTO email) {
        userService.updateUserEmail(email);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 修改用户头像
     * @Date 22:30 2024/6/10
     * @Param [file]
     * @return com.tanyinghao.comm.result.Result<java.lang.String>
     **/
    @ApiOperation("修改用户头像")
    @SaCheckPermission("user:avatar:update")
    @PostMapping("/avatar")
    public Result<String> updateUserAvatar(@RequestParam(value = "file") MultipartFile file) {
        return Result.success(userService.updateUserAvatar(file));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 修改用户信息
     * @Date 22:34 2024/6/10
     * @Param [userInfo]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ApiOperation("修改用户信息")
    @SaCheckPermission("user:info:update")
    @PutMapping("/info")
    public Result<?> updateUserInfo(@Validated @RequestBody UserInfoDTO userInfo) {
        userService.updateUserInfo(userInfo);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 修改用户密码
     * @Date 22:37 2024/6/10
     * @Param [user]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ApiOperation("修改用户密码")
    @PutMapping("/password")
    public Result<?> updatePassword(@Validated @RequestBody UserDTO user) {
        userService.updatePassword(user);
        return Result.success();
    }

}
