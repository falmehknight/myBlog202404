package com.tanyinghao.controller.admin;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.*;
import com.tanyinghao.model.vo.ArticleBackVO;
import com.tanyinghao.model.vo.ArticleInfoVO;
import com.tanyinghao.model.vo.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName ArticleController
 * @Description 后台管理端文章控制器
 * @Author 谭颍豪
 * @Date 2024/5/8 23:37
 * @Version 1.0
 **/
@RestController
@Api(tags = "管理文章模块")
@RequestMapping("/admin/article")
public class ArticleController {

    /**
     *
     * @Author TanYingHao
     * @Description 查看后台文章列表
     * @Date 22:12 2024/5/10
     * @Param [condition]
     * @return 后台文章列表
     **/
    @ApiOperation(value = "查看后台文章列表")
    @GetMapping("/list")
    @SaCheckPermission("blog:article:list")
    public Result<PageResult<ArticleBackVO>> listArticleBackVO(ConditionDTO condition) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 添加文章
     * @Date 22:22 2024/5/10
     * @Param [article] 文章信息
     * @return com.tanyinghao.comm.result.Result<?> 返回结果
     **/
    @ApiOperation(value = "添加文章")
    @PostMapping("/add")
    @SaCheckPermission("blog:article:add")
    public Result<?> addArticle(@Validated @RequestBody ArticleDTO article) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 删除文章
     * @Date 22:25 2024/5/10
     * @Param [articleIdList] 待删除文章的id集合
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ApiOperation(value = "删除文章")
    @DeleteMapping("/delete")
    @SaCheckPermission("blog:article:delete")
    public Result<?> deleteArticle(@RequestBody List<Integer> articleIdList) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 回收或者恢复文章
     * @Date 22:30 2024/5/10
     * @Param [delete]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ApiOperation(value = "回收或者恢复文章")
    @PutMapping("/recycle")
    @SaCheckPermission("blog:article:recycle")
    public Result<?> updateArticleDelete(@Validated @RequestBody DeleteDTO delete) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 修改文章
     * @Date 22:33 2024/5/10
     * @Param [article]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ApiOperation(value = "修改文章")
    @PutMapping("/update")
    @SaCheckPermission("blog:article:update")
    public Result<?> updateArticle(@Validated @RequestBody ArticleDTO article) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 编辑文章
     * @Date 22:36 2024/5/10
     * @Param [articleId]
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.ArticleInfoVO>
     **/
    @ApiOperation(value = "编辑文章")
    @GetMapping("/edit/{articleId}")
    @SaCheckPermission("blog:article:edit")
    public Result<ArticleInfoVO> editArticle(@PathVariable("articleId") Integer articleId) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 上传文章图片
     * @Date 22:41 2024/5/10
     * @Param [file] 文件
     * @return com.tanyinghao.comm.result.Result<java.lang.String> 文章图片地址
     **/
    @ApiOperation(value = "上传文章图片")
    @PostMapping("/upload")
    @SaCheckPermission("blog:article:upload")
    public Result<String> saveArticleImages(@RequestParam("file") MultipartFile file) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 置顶文章
     * @Date 22:44 2024/5/10
     * @Param [top] 置顶信息
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ApiOperation(value = "置顶文章")
    @PutMapping("/top")
    @SaCheckPermission("blog:article:top")
    public Result<?> updateArticleTop(@Validated @RequestBody TopDTO top) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 推荐文章
     * @Date 22:48 2024/5/10
     * @Param [recommend] 推荐信息
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ApiOperation(value = "推荐文章")
    @PutMapping("/recommend")
    @SaCheckPermission("blog:article:recommend")
    public Result<?> updateArticleRecommend(@Validated @RequestBody RecommendDTO recommend) {
        return Result.success();
    }

}
