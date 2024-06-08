package com.tanyinghao.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tanyinghao.comm.annotation.OptLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.MenuDTO;
import com.tanyinghao.model.vo.MenuOption;
import com.tanyinghao.model.vo.MenuTree;
import com.tanyinghao.model.vo.MenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tanyinghao.comm.constant.OptTypeConstant.*;

/**
 * @ClassName MenuController
 * @Description 菜单模块
 * @Author 谭颍豪
 * @Date 2024/6/8 23:19
 * @Version 1.0
 **/
@Api(tags = "菜单模块")
@RestController
@RequestMapping("/admin/menu")
public class MenuController {

    /**
     *
     * @Author TanYingHao
     * @Description 查看菜单列表
     * @Date 23:26 2024/6/8
     * @Param [condition]
     * @return com.tanyinghao.comm.result.Result<java.util.List<com.tanyinghao.model.vo.MenuVO>>
     **/
    @ApiOperation("查看菜单列表")
    @SaCheckPermission("system:menu:list")
    @GetMapping("/list")
    public Result<List<MenuVO>> listMenuVO(ConditionDTO condition) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 添加菜单
     * @Date 23:28 2024/6/8
     * @Param [menu]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = ADD)
    @ApiOperation("添加菜单")
    @SaCheckPermission("system:menu:add")
    @PostMapping("/add")
    public Result<?> addMenu(@Validated @RequestBody MenuDTO menu) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 删除菜单
     * @Date 23:30 2024/6/8
     * @Param [menuId]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = DELETE)
    @ApiOperation("删除菜单")
    @SaCheckPermission("system:menu:delete")
    @DeleteMapping("/delete/{menuId}")
    public Result<?> deleteMenu(@PathVariable("menuId") Integer menuId) {
        return Result.success();
    }


    /**
     *
     * @Author TanYingHao
     * @Description 修改菜单
     * @Date 23:33 2024/6/8
     * @Param [menu]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "修改菜单")
    @SaCheckPermission("system:menu:update")
    @PutMapping("/update")
    public Result<?> updateMenu(@Validated @RequestBody MenuDTO menu) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 获取菜单下拉树
     * @Date 23:38 2024/6/8
     * @Param []
     * @return com.tanyinghao.comm.result.Result<java.util.List<com.tanyinghao.model.vo.MenuTree>>
     **/
    @ApiOperation("获取菜单下拉树")
    @SaCheckPermission("system:role:list")
    @GetMapping("/getMenuTree")
    public Result<List<MenuTree>> listMenuTree() {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 获取菜单选项树
     * @Date 23:39 2024/6/8
     * @Param []
     * @return com.tanyinghao.comm.result.Result<java.util.List<com.tanyinghao.model.vo.MenuOption>>
     **/
    @ApiOperation("获取菜单选项树")
    @SaCheckPermission("system:role:list")
    @GetMapping("/getMenuOptions")
    public Result<List<MenuOption>> listMenuOption() {
        return Result.success();
    }
}
