package com.tanyinghao.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tanyinghao.comm.annotation.OptLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.entity.SiteConfig;
import com.tanyinghao.service.SiteConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.tanyinghao.comm.constant.OptTypeConstant.UPDATE;
import static com.tanyinghao.comm.constant.OptTypeConstant.UPLOAD;

/**
 * @ClassName SiteConfigController
 * @Description 网站配置控制器
 * @Author 谭颍豪
 * @Date 2024/6/13 11:17
 * @Version 1.0
 **/
@Api(tags = "网站配置模块")
@RestController
@RequestMapping("/admin/site")
public class SiteConfigController {

    @Autowired
    private SiteConfigService siteConfigService;

   /**
    *
    * @Author TanYingHao
    * @Description 获取网站配置
    * @Date 11:26 2024/6/13
    * @Param []
    * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.entity.SiteConfig>
    **/
    @ApiOperation(value = "获取网站配置")
    @SaCheckPermission("web:site:list")
    @GetMapping("/list")
    public Result<SiteConfig> getSiteConfig() {
        return Result.success(siteConfigService.getSiteConfig());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 更新网站配置
     * @Date 11:36 2024/6/13
     * @Param [siteConfig]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "更新网站配置")
    @SaCheckPermission("web:site:update")
    @PutMapping("/update")
    public Result<?> updateSiteConfig(@RequestBody SiteConfig siteConfig) {
        siteConfigService.updateSiteConfig(siteConfig);
        return Result.success();
    }

    /**
    *
    * @Author TanYingHao
    * @Description 上传网站配置图片
    * @Date 11:37 2024/6/13
    * @Param [file]
    * @return com.tanyinghao.comm.result.Result<java.lang.String>
    **/
    @OptLogger(value = UPLOAD)
    @ApiOperation(value = "上传网站配置图片")
    @ApiImplicitParam(name = "file", value = "配置图片", required = true, dataType = "MultipartFile")
    @SaCheckPermission("web:site:upload")
    @PostMapping("/upload")
    public Result<String> uploadSiteImg(@RequestParam("file") MultipartFile file) {
        return Result.success(siteConfigService.uploadSiteImg(file));
    }
}
