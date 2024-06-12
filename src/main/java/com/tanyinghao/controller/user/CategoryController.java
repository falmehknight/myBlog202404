package com.tanyinghao.controller.user;

import com.tanyinghao.comm.annotation.VisitLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.vo.ArticleConditionList;
import com.tanyinghao.model.vo.CategoryVO;
import com.tanyinghao.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CategoryController
 * @Description 分类控制器
 * @Author 谭颍豪
 * @Date 2024/6/13 0:11
 * @Version 1.0
 **/
@Api(tags = "前台分类管理模块")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    /**
     *
     * @Author TanYingHao
     * @Description 查看分类列表
     * @Date 0:12 2024/6/13
     * @Param []
     * @return com.tanyinghao.comm.result.Result<java.util.List<com.tanyinghao.model.vo.CategoryVO>>
     **/
    @VisitLogger(value = "文章分类")
    @ApiOperation(value = "查看分类列表")
    @GetMapping("/list")
    public Result<List<CategoryVO>> listCategoryVO() {
        return Result.success(categoryService.listCategoryVO());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看分类下的文章
     * @Date 0:13 2024/6/13
     * @Param [condition]
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.ArticleConditionList>
     **/
    @VisitLogger(value = "分类文章")
    @ApiOperation(value = "查看分类下的文章")
    @GetMapping("/article")
    public Result<ArticleConditionList> listArticleCategory(ConditionDTO condition) {
        return Result.success(categoryService.listArticleCategory(condition));
    }
}
