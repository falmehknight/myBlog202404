package com.tanyinghao.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tanyinghao.comm.annotation.OptLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.FolderDTO;
import com.tanyinghao.model.vo.FileVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.service.BlogFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.tanyinghao.comm.constant.OptTypeConstant.*;

/**
 * @ClassName BlogFileController
 * @Description 文件控制器
 * @Author 谭颍豪
 * @Date 2024/6/12 20:42
 * @Version 1.0
 **/
@Api(tags = "后台文件模块")
@RestController
@RequestMapping("/admin/file")
public class BlogFileController {
    @Autowired
    private BlogFileService fileService;

    /**
     *
     * @Author TanYingHao
     * @Description 查看文件列表
     * @Date 20:46 2024/6/12
     * @Param [condition]
     * @return com.tanyinghao.comm.result.Result<PageResult<FileVO>>
     **/
    @ApiOperation(value = "查看文件列表")
    @SaCheckPermission("system:file:list")
    @GetMapping("/list")
    public Result<PageResult<FileVO>> listFileVOList(ConditionDTO condition) {
        return Result.success(fileService.listFileVOList(condition));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 上传文件
     * @Date 20:49 2024/6/12
     * @Param [file, path]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = UPLOAD)
    @ApiOperation(value = "上传文件")
    @ApiImplicitParam(name = "file", value = "图片", required = true, dataType = "MultipartFile")
    @SaCheckPermission("system:file:upload")
    @PostMapping("/upload")
    public Result<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("path") String path) {
        fileService.uploadFile(file, path);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 创建目录
     * @Date 20:50 2024/6/12
     * @Param [folder]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = ADD)
    @ApiOperation(value = "创建目录")
    @SaCheckPermission("system:file:createFolder")
    @PostMapping("/createFolder")
    public Result<?> createFolder(@Validated @RequestBody FolderDTO folder) {
        fileService.createFolder(folder);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 删除文件
     * @Date 20:52 2024/6/12
     * @Param [fileIdList]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = DELETE)
    @ApiOperation(value = "删除文件")
    @SaCheckPermission("system:file:delete")
    @DeleteMapping("/admin/file/delete")
    public Result<?> deleteFile(@RequestBody List<Integer> fileIdList) {
        fileService.deleteFile(fileIdList);
        return Result.success();
    }
}
