package com.tanyinghao.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import com.tanyinghao.comm.annotation.OptLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.RoleDTO;
import com.tanyinghao.model.dto.RoleStatusDTO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tanyinghao.comm.constant.OptTypeConstant.*;

/**
 * @ClassName RoleController
 * @Description 角色控制器
 * @Author 谭颍豪
 * @Date 2024/6/7 9:42
 * @Version 1.0
 **/
@Api(tags = "角色模块")
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    /**
     *
     * @Author TanYingHao
     * @Description 查看角色列表
     * @Date 9:46 2024/6/7
     * @Param [condition]
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.RoleVO>>
     **/
    @ApiOperation(value = "查看角色列表")
    @SaCheckPermission("system:role:list")
    @GetMapping("/list")
    public Result<PageResult<RoleVO>> listRoleVO(ConditionDTO condition) {
        return Result.success();
    }


    /**
     *
     * @Author TanYingHao
     * @Description 添加角色
     * @Date 9:55 2024/6/7
     * @Param [role]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = ADD)
    @ApiOperation("添加角色")
    @SaCheckPermission("system:role:add")
    @PostMapping("/add")
    public Result<?> addRole(@Validated @RequestBody RoleDTO role) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 删除角色
     * @Date 9:59 2024/6/7
     * @Param [roleIdList]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = DELETE)
    @ApiOperation("删除角色")
    @SaCheckPermission("system:role:delete")
    @DeleteMapping("/delete")
    public Result<?> deleteRole(@RequestBody List<String> roleIdList) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 更新角色
     * @Date 10:01 2024/6/7
     * @Param [role]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = UPDATE)
    @ApiOperation("修改角色")
    @SaCheckPermission("system:role:update")
    @PutMapping("/update")
    public Result<?> updateRole(@Validated @RequestBody RoleDTO role) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 修改角色状态
     * @Date 10:08 2024/6/7
     * @Param [roleStatus]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ApiOperation(value = "修改角色状态")
    // 标识只要有任意一个权限即可进入
    @SaCheckPermission(value = {"system:role:update", "system:role:status"}, mode = SaMode.OR)
    @PutMapping("/changeStatus")
    public Result<?> updateRoleStatus(@Validated @RequestBody RoleStatusDTO roleStatus) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看角色的菜单权限
     * @Date 10:12 2024/6/7
     * @Param [roleId]
     * @return com.tanyinghao.comm.result.Result<java.util.List<java.lang.Integer>>
     **/
    @ApiOperation("查看角色的菜单权限")
    @SaCheckPermission("system:role:list")
    @GetMapping("/menu/{roleId}")
    public Result<List<Integer>> listRoleMenuTree(@PathVariable("roleId") String roleId) {
        return Result.success();
    }
}
