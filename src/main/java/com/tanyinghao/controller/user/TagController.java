package com.tanyinghao.controller.user;

import com.tanyinghao.comm.annotation.VisitLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.vo.ArticleConditionList;
import com.tanyinghao.model.vo.TagVO;
import com.tanyinghao.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName TagController
 * @Description 前台标签模块
 * @Author 谭颍豪
 * @Date 2024/6/12 23:39
 * @Version 1.0
 **/
@Api(tags = "前台标签模块")
@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

   /**
    *
    * @Author TanYingHao
    * @Description 查看标签列表
    * @Date 23:40 2024/6/12
    * @Param []
    * @return com.tanyinghao.comm.result.Result<java.util.List<TagVO>>
    **/
    @VisitLogger(value = "文章标签")
    @ApiOperation(value = "查看标签列表")
    @GetMapping("/list")
    public Result<List<TagVO>> listTagVO() {
        return Result.success(tagService.listTagVO());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看标签下的文章
     * @Date 23:43 2024/6/12
     * @Param [condition]
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.ArticleConditionList>
     **/
    @VisitLogger(value = "标签文章")
    @ApiOperation(value = "查看标签下的文章")
    @GetMapping("/article")
    public Result<ArticleConditionList> listArticleTag(ConditionDTO condition) {
        return Result.success(tagService.listArticleTag(condition));
    }
}
