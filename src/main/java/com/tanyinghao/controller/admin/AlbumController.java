package com.tanyinghao.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tanyinghao.comm.annotation.OptLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.AlbumDTO;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.vo.AlbumBackVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.service.AlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.tanyinghao.comm.constant.OptTypeConstant.*;

/**
 * @ClassName AlbumController
 * @Description 相册控制器
 * @Author 谭颍豪
 * @Date 2024/6/15 14:39
 * @Version 1.0
 **/
@Api(tags = "后台相册管理模块")
@RestController
@RequestMapping("/admin/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

   /**
    *
    * @Author TanYingHao
    * @Description 查看后台相册列表
    * @Date 14:42 2024/6/15
    * @Param [condition]
    * @return com.tanyinghao.comm.result.Result<PageResult<AlbumBackVO>>
    **/
    @ApiOperation(value = "查看后台相册列表")
    @SaCheckPermission("web:album:list")
    @GetMapping("/list")
    public Result<PageResult<AlbumBackVO>> listAlbumBackVO(ConditionDTO condition) {
        return Result.success(albumService.listAlbumBackVO(condition));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 上传相册封面
     * @Date 14:44 2024/6/15
     * @Param [file]
     * @return com.tanyinghao.comm.result.Result<java.lang.String>
     **/
    @OptLogger(value = UPLOAD)
    @ApiOperation(value = "上传相册封面")
    @ApiImplicitParam(name = "file", value = "相册封面", required = true, dataType = "MultipartFile")
    @SaCheckPermission("web:album:upload")
    @PostMapping("/upload")
    public Result<String> uploadAlbumCover(@RequestParam("file") MultipartFile file) {
        return Result.success(albumService.uploadAlbumCover(file));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 添加相册
     * @Date 14:44 2024/6/15
     * @Param [album]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = ADD)
    @ApiOperation(value = "添加相册")
    @SaCheckPermission("web:album:add")
    @PostMapping("/add")
    public Result<?> addAlbum(@Validated @RequestBody AlbumDTO album) {
        albumService.addAlbum(album);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 删除相册
     * @Date 14:47 2024/6/15
     * @Param [albumId]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = DELETE)
    @ApiOperation(value = "删除相册")
    @SaCheckPermission("web:album:delete")
    @DeleteMapping("/delete/{albumId}")
    public Result<?> deleteAlbum(@PathVariable("albumId") Integer albumId) {
        albumService.deleteAlbum(albumId);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 修改相册
     * @Date 14:48 2024/6/15
     * @Param [album]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "修改相册")
    @SaCheckPermission("web:album:update")
    @PutMapping("/update")
    public Result<?> updateAlbum(@Validated @RequestBody AlbumDTO album) {
        albumService.updateAlbum(album);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 编辑相册
     * @Date 14:48 2024/6/15
     * @Param [albumId]
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.dto.AlbumDTO>
     **/
    @ApiOperation(value = "编辑相册")
    @SaCheckPermission("web:album:edit")
    @GetMapping("/edit/{albumId}")
    public Result<AlbumDTO> editAlbum(@PathVariable("albumId") Integer albumId) {
        return Result.success(albumService.editAlbum(albumId));
    }
}
