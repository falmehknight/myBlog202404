package com.tanyinghao.controller.user;

import com.tanyinghao.comm.result.Result;
import com.tanyinghao.service.BiliService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName BiliController
 * @Description B站服务控制器
 * @Author 谭颍豪
 * @Date 2024/6/15 15:07
 * @Version 1.0
 **/
@Api(tags = "B站服务模块")
@RestController
public class BiliController {
    @Autowired
    private BiliService biliService;

    /**
     *
     * @Author TanYingHao
     * @Description B站图片上传
     * @Date 15:11 2024/6/15
     * @Param [file, csrf, data]
     * @return com.tanyinghao.comm.result.Result<java.lang.String>
     **/
    @ApiOperation(value = "B站图片上传")
    @PostMapping("/bili/upload")
    public Result<String> biliUpload(@RequestParam("file_up") MultipartFile file,
                                     @RequestParam(value = "csrf") String csrf,
                                     @RequestParam(value = "data") String data) {
        return Result.success(biliService.uploadBiliPicture(file, csrf, data));
    }
}
