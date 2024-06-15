package com.tanyinghao.controller.user;

import com.tanyinghao.comm.annotation.VisitLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.vo.AlbumVO;
import com.tanyinghao.service.AlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName AlbumController
 * @Description 相册控制器
 * @Author 谭颍豪
 * @Date 2024/6/15 15:01
 * @Version 1.0
 **/
@Api(tags = "前台相册管理模块")
@RestController
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    /**
     *
     * @Author TanYingHao
     * @Description 查看相册列表
     * @Date 15:02 2024/6/15
     * @Param []
     * @return com.tanyinghao.comm.result.Result<java.util.List<AlbumVO>>
     **/
    @VisitLogger(value = "相册")
    @ApiOperation(value = "查看相册列表")
    @GetMapping("/album/list")
    public Result<List<AlbumVO>> listAlbumVO() {
        return Result.success(albumService.listAlbumVO());
    }
}
