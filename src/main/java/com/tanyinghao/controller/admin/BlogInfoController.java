package com.tanyinghao.controller.admin;

import com.tanyinghao.comm.annotation.VisitLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.vo.BlogBackInfoVO;
import com.tanyinghao.model.vo.BlogInfoVO;
import com.tanyinghao.service.BlogInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BlogInfoController
 * @Description 博客控制器
 * @Author 谭颍豪
 * @Date 2024/6/15 18:33
 * @Version 1.0
 **/
@Api(tags = "博客模块")
@RestController
public class BlogInfoController {

    @Autowired
    private BlogInfoService blogInfoService;

    /**
     *
     * @Author TanYingHao
     * @Description 上传访客信息
     * @Date 18:34 2024/6/15
     * @Param []
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ApiOperation(value = "上传访客信息")
    @PostMapping("/report")
    public Result<?> report() {
        blogInfoService.report();
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看博客信息
     * @Date 18:35 2024/6/15
     * @Param []
     * @return com.tanyinghao.comm.result.Result<BlogInfoVO>
     **/
    @ApiOperation(value = "查看博客信息")
    @GetMapping("/")
    public Result<BlogInfoVO> getBlogInfo() {
        return Result.success(blogInfoService.getBlogInfo());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看后台信息
     * @Date 18:36 2024/6/15
     * @Param []
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.BlogBackInfoVO>
     **/
    @ApiOperation(value = "查看后台信息")
    @GetMapping("/admin")
    public Result<BlogBackInfoVO> getBlogBackInfo() {
        return Result.success(blogInfoService.getBlogBackInfo());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看关于我信息
     * @Date 18:37 2024/6/15
     * @Param []
     * @return com.tanyinghao.comm.result.Result<java.lang.String>
     **/
    @VisitLogger(value = "关于")
    @ApiOperation(value = "查看关于我信息")
    @GetMapping("/about")
    public Result<String> getAbout() {
        return Result.success(blogInfoService.getAbout());
    }
}
