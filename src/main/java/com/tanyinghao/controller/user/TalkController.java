package com.tanyinghao.controller.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tanyinghao.comm.annotation.AccessLimit;
import com.tanyinghao.comm.annotation.OptLogger;
import com.tanyinghao.comm.annotation.VisitLogger;
import com.tanyinghao.comm.enums.LikeTypeEnum;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.TalkVO;
import com.tanyinghao.service.TalkService;
import com.tanyinghao.strategy.context.LikeStrategyContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName TalkController
 * @Description 说说控制器
 * @Author 谭颍豪
 * @Date 2024/6/14 9:51
 * @Version 1.0
 **/
@Api(tags = "前台说说模块")
@RestController
public class TalkController {

    @Autowired
    private TalkService talkService;

    @Autowired
    private LikeStrategyContext likeStrategyContext;

    /**
     *
     * @Author TanYingHao
     * @Description 点赞说说
     * @Date 11:22 2024/6/14
     * @Param [talkId]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @SaCheckLogin
    @ApiOperation(value = "点赞说说")
    @AccessLimit(seconds = 60, maxCount = 3)
    @SaCheckPermission("web:talk:like")
    @PostMapping("/talk/{talkId}/like")
    public Result<?> saveTalkLike(@PathVariable("talkId") Integer talkId) {
        likeStrategyContext.executeLikeStrategy(LikeTypeEnum.TALK, talkId);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看首页说说
     * @Date 11:22 2024/6/14
     * @Param []
     * @return com.tanyinghao.comm.result.Result<java.util.List<java.lang.String>>
     **/
    @ApiOperation(value = "查看首页说说")
    @GetMapping("/home/talk")
    public Result<List<String>> listTalkHome() {
        return Result.success(talkService.listTalkHome());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看说说列表
     * @Date 11:24 2024/6/14
     * @Param []
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.TalkVO>>
     **/
    @VisitLogger(value = "说说列表")
    @ApiOperation(value = "查看说说列表")
    @GetMapping("/talk/list")
    public Result<PageResult<TalkVO>> listTalkVO() {
        return Result.success(talkService.listTalkVO());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看说说
     * @Date 11:25 2024/6/14
     * @Param [talkId]
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.TalkVO>
     **/
    @VisitLogger(value = "说说")
    @ApiOperation(value = "查看说说")
    @GetMapping("/talk/{talkId}")
    public Result<TalkVO> getTalkById(@PathVariable("talkId") Integer talkId) {
        return Result.success(talkService.getTalkById(talkId));
    }


}
