package com.tanyinghao.controller.user;

import com.tanyinghao.comm.annotation.VisitLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.vo.FriendVO;
import com.tanyinghao.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName FriendController
 * @Description 友链控制器
 * @Author 谭颍豪
 * @Date 2024/6/15 18:24
 * @Version 1.0
 **/
@Api(tags = "前台友链模块")
@RestController
public class FriendController {

    @Autowired
    private FriendService friendService;

    /**
     *
     * @Author TanYingHao
     * @Description 查看友链列表
     * @Date 18:25 2024/6/15
     * @Param []
     * @return com.tanyinghao.comm.result.Result<java.util.List<FriendVO>>
     **/
    @VisitLogger(value = "友链")
    @ApiOperation(value = "查看友链列表")
    @GetMapping("/friend/list")
    public Result<List<FriendVO>> listFriendVO() {
        return Result.success(friendService.listFriendVO());
    }
}
