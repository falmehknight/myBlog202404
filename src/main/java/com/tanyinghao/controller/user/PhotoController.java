package com.tanyinghao.controller.user;

import com.tanyinghao.comm.annotation.VisitLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.service.PhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName PhotoController
 * @Description  照片控制器
 * @Author 谭颍豪
 * @Date 2024/6/15 14:32
 * @Version 1.0
 **/
@Api(tags = "前台照片模块")
@RestController
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    /**
     *
     * @Author TanYingHao
     * @Description 查看照片列表
     * @Date 14:33 2024/6/15
     * @Param [condition]
     * @return com.tanyinghao.comm.result.Result<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @VisitLogger(value = "照片")
    @ApiOperation(value = "查看照片列表")
    @GetMapping("/photo/list")
    public Result<Map<String, Object>> listPhotoVO(ConditionDTO condition) {
        return Result.success(photoService.listPhotoVO(condition));
    }
}
