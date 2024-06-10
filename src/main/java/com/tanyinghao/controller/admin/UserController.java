package com.tanyinghao.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.tanyinghao.comm.annotation.OptLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.DisableDTO;
import com.tanyinghao.model.dto.PasswordDTO;
import com.tanyinghao.model.dto.UserRoleDTO;
import com.tanyinghao.model.vo.*;
import com.tanyinghao.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tanyinghao.comm.constant.OptTypeConstant.KICK;
import static com.tanyinghao.comm.constant.OptTypeConstant.UPDATE;

/**
 * @ClassName UserController
 * @Description 用户模块
 * @Author 谭颍豪
 * @Date 2024/6/9 14:36
 * @Version 1.0
 **/
@Api("用户模块")
@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     *
     * @Author TanYingHao
     * @Description 后缺后台登录用户信息
     * @Date 14:41 2024/6/9
     * @Param []
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.UserBackInfoVO>
     **/
    @ApiOperation("获取后台登录用户信息")
    @GetMapping("/user/getUserInfo")
    public Result<UserBackInfoVO> getUserBackInfo() {
        return Result.success(userService.getUserBackInfo());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 获取登录用户菜单
     * @Date 14:48 2024/6/9
     * @Param []
     * @return com.tanyinghao.comm.result.Result<java.util.List<com.tanyinghao.model.vo.RouterVO>>
     **/
    @ApiOperation("获取登录用户菜单")
    @GetMapping("/user/getUserMenu")
    public Result<List<RouterVO>> getUserMenu() {
        return Result.success(userService.getUserMenu());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看后台用户列表
     * @Date 14:52 2024/6/9
     * @Param [condition]
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.UserBackVO>>
     **/
    @ApiOperation("查看后台用户列表")
    @SaCheckPermission("system:user:list")
    @GetMapping("/user/list")
    public Result<PageResult<UserBackVO>> listUserBackVO(ConditionDTO condition) {
        return Result.success(userService.listUserBackVO(condition));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看用户角色选项
     * @Date 14:54 2024/6/9
     * @Param []
     * @return com.tanyinghao.comm.result.Result<java.util.List<com.tanyinghao.model.vo.UserRoleVO>>
     **/
    @ApiOperation("查看用户角色选项")
    @SaCheckPermission("system:user:list")
    @GetMapping("/user/role")
    public Result<List<UserRoleVO>> listUserRoleVO() {
        return Result.success(userService.listUserRoleVO());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 修改用户
     * @Date 14:59 2024/6/9
     * @Param [userRole]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = UPDATE)
    @ApiOperation("修改用户")
    @SaCheckPermission("system:user:update")
    @PutMapping("/user/update")
    public Result<?> updateUser(@Validated @RequestBody UserRoleDTO userRole) {
        userService.updateUser(userRole);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 修改用户状态
     * @Date 15:10 2024/6/9
     * @Param [disable] 禁用信息
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = UPDATE)
    @ApiOperation("修改用户状态")
    @SaCheckPermission("system:user:status")
    @PutMapping("/user/changeStatus")
    public Result<?> updateUserStatus(@Validated @RequestBody DisableDTO disable) {
        userService.updateUserStatus(disable);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看在线用户
     * @Date 15:14 2024/6/9
     * @Param [condition]
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.OnlineVO>>
     **/
    @ApiOperation("查看在线用户")
    @SaCheckPermission("monitor:online:list")
    @GetMapping("/user/list")
    public Result<PageResult<OnlineVO>> listOnlineUser(ConditionDTO condition) {
        return Result.success(userService.listOnlineUser(condition));
    }


    /**
     *
     * @Author TanYingHao
     * @Description 下线用户
     * @Date 15:18 2024/6/9
     * @Param [token]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = KICK)
    @ApiOperation("下线用户")
    @SaCheckPermission("monitor:online:kick")
    @GetMapping("/online/kick/{token}")
    public Result<?> kickOutUser(@PathVariable("token") String token) {
        userService.kickOutUser(token);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 修改管理员密码
     * @Date 15:20 2024/6/9
     * @Param [password]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @SaCheckRole("1")
    @ApiOperation(value = "修改管理员密码")
    @PutMapping("/password")
    public Result<?> updateAdminPassword(@Validated @RequestBody PasswordDTO password) {
        userService.updateAdminPassword(password);
        return Result.success();
    }

}
