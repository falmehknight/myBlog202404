package com.tanyinghao.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tanyinghao.comm.annotation.OptLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.CategoryDTO;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.vo.CategoryBackVO;
import com.tanyinghao.model.vo.CategoryOptionVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tanyinghao.comm.constant.OptTypeConstant.*;

/**
 * @ClassName CategoryController
 * @Description 分类控制器
 * @Author 谭颍豪
 * @Date 2024/6/12 23:56
 * @Version 1.0
 **/
@Api(tags = "后台分类管理模块")
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

   /**
    *
    * @Author TanYingHao
    * @Description 查看后台分类列表
    * @Date 0:00 2024/6/13
    * @Param [condition]
    * @return com.tanyinghao.comm.result.Result<PageResult<CategoryBackVO>>
    **/
    @ApiOperation(value = "查看后台分类列表")
    @SaCheckPermission("blog:category:list")
    @GetMapping("/list")
    public Result<PageResult<CategoryBackVO>> listCategoryBackVO(ConditionDTO condition) {
        return Result.success(categoryService.listCategoryBackVO(condition));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 添加分类
     * @Date 0:02 2024/6/13
     * @Param [category]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = ADD)
    @ApiOperation(value = "添加分类")
    @SaCheckPermission("blog:category:add")
    @PostMapping("/add")
    public Result<?> addCategory(@Validated @RequestBody CategoryDTO category) {
        categoryService.addCategory(category);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 删除分类
     * @Date 0:04 2024/6/13
     * @Param [categoryIdList]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = DELETE)
    @ApiOperation(value = "删除分类")
    @SaCheckPermission("blog:category:delete")
    @DeleteMapping("/delete")
    public Result<?> deleteCategory(@RequestBody List<Integer> categoryIdList) {
        categoryService.deleteCategory(categoryIdList);
        return Result.success();
    }

   /**
    *
    * @Author TanYingHao
    * @Description 修改分类
    * @Date 0:04 2024/6/13
    * @Param [category] 分类属性
    * @return com.tanyinghao.comm.result.Result<?>
    **/
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "修改分类")
    @SaCheckPermission("blog:category:update")
    @PutMapping("/update")
    public Result<?> updateCategory(@Validated @RequestBody CategoryDTO category) {
        categoryService.updateCategory(category);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看分类选项
     * @Date 0:06 2024/6/13
     * @Param []
     * @return com.tanyinghao.comm.result.Result<java.util.List<com.tanyinghao.model.vo.CategoryOptionVO>>
     **/
    @ApiOperation(value = "查看分类选项")
    @GetMapping("/option")
    public Result<List<CategoryOptionVO>> listCategoryOption() {
        return Result.success(categoryService.listCategoryOption());
    }

}
