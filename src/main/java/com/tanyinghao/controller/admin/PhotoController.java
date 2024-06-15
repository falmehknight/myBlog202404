package com.tanyinghao.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tanyinghao.comm.annotation.OptLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.PhotoDTO;
import com.tanyinghao.model.dto.PhotoInfoDTO;
import com.tanyinghao.model.vo.AlbumBackVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.PhotoBackVO;
import com.tanyinghao.service.PhotoService;
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
 * @ClassName PhotoController
 * @Description 照片控制器
 * @Author 谭颍豪
 * @Date 2024/6/15 13:37
 * @Version 1.0
 **/
@Api(tags = "后台照片管理模块")
@RestController
@RequestMapping("/admin/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    /**
     *
     * @Author TanYingHao
     * @Description 查看后台照片列表
     * @Date 13:46 2024/6/15
     * @Param [condition]
     * @return com.tanyinghao.comm.result.Result<PageResult<PhotoBackVO>>
     **/
    @ApiOperation(value = "查看后台照片列表")
    @SaCheckPermission("web:photo:list")
    @GetMapping("/list")
    public Result<PageResult<PhotoBackVO>> listPhotoBackVO(ConditionDTO condition) {
        return Result.success(photoService.listPhotoBackVO(condition));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看照片相册信息
     * @Date 13:50 2024/6/15
     * @Param [albumId]
     * @return com.tanyinghao.comm.result.Result<AlbumBackVO>
     **/
    @ApiOperation(value = "查看照片相册信息")
    @SaCheckPermission("web:photo:list")
    @GetMapping("album/{albumId}/info")
    public Result<AlbumBackVO> getAlbumInfo(@PathVariable("albumId") Integer albumId) {
        return Result.success(photoService.getAlbumInfo(albumId));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 上传照片
     * @Date 13:52 2024/6/15
     * @Param [file]
     * @return com.tanyinghao.comm.result.Result<java.lang.String>
     **/
    @OptLogger(value = UPLOAD)
    @ApiOperation(value = "上传照片")
    @ApiImplicitParam(name = "file", value = "照片", required = true, dataType = "MultipartFile")
    @SaCheckPermission("web:photo:upload")
    @PostMapping("/upload")
    public Result<String> uploadPhoto(@RequestParam("file") MultipartFile file) {
        return Result.success(photoService.uploadPhoto(file));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 添加照片
     * @Date 13:53 2024/6/15
     * @Param [photo]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = ADD)
    @ApiOperation(value = "添加照片")
    @SaCheckPermission("web:photo:add")
    @PostMapping("/add")
    public Result<?> addPhoto(@Validated @RequestBody PhotoDTO photo) {
        photoService.addPhoto(photo);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 修改照片信息
     * @Date 13:54 2024/6/15
     * @Param [photoInfo]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "修改照片信息")
    @SaCheckPermission("web:photo:update")
    @PutMapping("/update")
    public Result<?> updatePhoto(@Validated @RequestBody PhotoInfoDTO photoInfo) {
        photoService.updatePhoto(photoInfo);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 删除图片
     * @Date 13:56 2024/6/15
     * @Param [photoIdList]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = DELETE)
    @ApiOperation(value = "删除照片")
    @SaCheckPermission("web:photo:delete")
    @DeleteMapping("/delete")
    public Result<?> deletePhoto(@RequestBody List<Integer> photoIdList) {
        photoService.deletePhoto(photoIdList);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 移动图片
     * @Date 13:56 2024/6/15
     * @Param [photo]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "移动照片")
    @SaCheckPermission("web:photo:move")
    @PutMapping("/move")
    public Result<?> movePhoto(@Validated @RequestBody PhotoDTO photo) {
        photoService.movePhoto(photo);
        return Result.success();
    }
}
