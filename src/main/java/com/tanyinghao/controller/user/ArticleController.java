package com.tanyinghao.controller.user;

import com.tanyinghao.comm.annotation.VisitLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.vo.*;
import com.tanyinghao.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName ArticleController
 * @Description 前台文章模块
 * @Author 谭颍豪
 * @Date 2024/6/12 16:08
 * @Version 1.0
 **/
@RestController
@Api(tags = "前台文章模块")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     *
     * @Author TanYingHao
     * @Description 搜索文章
     * @Date 16:10 2024/6/12
     * @Param [keyword]
     * @return com.tanyinghao.comm.result.Result<java.util.List<ArticleSearchVO>>
     **/
    @ApiOperation(value = "搜索文章")
    @GetMapping("/article/search")
    public Result<List<ArticleSearchVO>> listArticlesBySearch(String keyword) {
        return Result.success(articleService.listArticlesBySearch(keyword));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看首页文章列表
     * @Date 16:12 2024/6/12
     * @Param []
     * @return com.tanyinghao.comm.result.Result<PageResult<ArticleHomeVO>>
     **/
    @VisitLogger(value = "首页")
    @ApiOperation(value = "查看首页文章列表")
    @GetMapping("/article/list")
    public Result<PageResult<ArticleHomeVO>> listArticleHomeVO() {
        return Result.success(articleService.listArticleHomeVO());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看文章
     * @Date 16:15 2024/6/12
     * @Param [articleId] 文章id
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.ArticleVO>
     **/
    @VisitLogger(value = "文章")
    @ApiOperation(value = "查看文章")
    @GetMapping("/article/{articleId}")
    public Result<ArticleVO> getArticleHomeById(@PathVariable("articleId") Integer articleId) {
        return Result.success(articleService.getArticleHomeById(articleId));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看推荐文章
     * @Date 16:16 2024/6/12
     * @Param []
     * @return com.tanyinghao.comm.result.Result<java.util.List<ArticleRecommendVO>>
     **/
    @ApiOperation(value = "查看推荐文章")
    @GetMapping("/article/recommend")
    public Result<List<ArticleRecommendVO>> listArticleRecommendVO() {
        return Result.success(articleService.listArticleRecommendVO());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看文章归档
     * @Date 16:18 2024/6/12
     * @Param []
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.PageResult<ArchiveVO>>
     **/
    @VisitLogger(value = "归档")
    @ApiOperation(value = "查看文章归档")
    @GetMapping("/archives/list")
    public Result<PageResult<ArchiveVO>> listArchiveVO() {
        return Result.success(articleService.listArchiveVO());
    }

}
