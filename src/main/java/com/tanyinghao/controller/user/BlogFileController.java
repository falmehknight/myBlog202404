package com.tanyinghao.controller.user;

import com.tanyinghao.comm.result.Result;
import com.tanyinghao.service.BlogFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BlogFileController
 * @Description 前台文件模块
 * @Author 谭颍豪
 * @Date 2024/6/12 21:41
 * @Version 1.0
 **/
@Api(tags = "前台文件模块")
@RestController("userBlogFileController")
public class BlogFileController {
    @Autowired
    private BlogFileService blogFileService;

    /**
     *
     * @Author TanYingHao
     * @Description 下载文件
     * @Date 21:43 2024/6/12
     * @Param [fileId] 文件id
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ApiOperation(value = "下载文件")
    @GetMapping("/file/download/{fileId}")
    public Result<?> downloadFile(@PathVariable("fileId") Integer fileId) {
        blogFileService.downloadFile(fileId);
        return Result.success();
    }
}
