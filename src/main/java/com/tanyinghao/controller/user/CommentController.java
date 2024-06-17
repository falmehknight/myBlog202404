package com.tanyinghao.controller.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tanyinghao.comm.annotation.AccessLimit;
import com.tanyinghao.comm.enums.LikeTypeEnum;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.CommentDTO;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.vo.CommentVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.RecentCommentVO;
import com.tanyinghao.model.vo.ReplyVO;
import com.tanyinghao.service.CommentService;
import com.tanyinghao.strategy.context.LikeStrategyContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CommentController
 * @Description 评论控制器
 * @Author 谭颍豪
 * @Date 2024/6/14 14:28
 * @Version 1.0
 **/
@Api(tags = "前台评论模块")
@RestController("userCommentController")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private LikeStrategyContext likeStrategyContext;

    /**
     * 添加评论
     *
     * @param comment 评论信息
     * @return {@link Result<>}
     */
    @SaCheckLogin
    @ApiOperation(value = "添加评论")
    @SaCheckPermission("news:comment:add")
    @PostMapping("/comment/add")
    public Result<?> addComment(@Validated @RequestBody CommentDTO comment) {
        commentService.addComment(comment);
        return Result.success();
    }


    /**
     *
     * @Author TanYingHao
     * @Description 点赞评论
     * @Date 16:27 2024/6/14
     * @Param [commentId]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @SaCheckLogin
    @ApiOperation(value = "点赞评论")
    @AccessLimit(seconds = 60, maxCount = 3)
    @SaCheckPermission("news:comment:like")
    @PostMapping("/comment/{commentId}/like")
    public Result<?> likeComment(@PathVariable("commentId") Integer commentId) {
        likeStrategyContext.executeLikeStrategy(LikeTypeEnum.COMMENT, commentId);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看最新评论
     * @Date 16:28 2024/6/14
     * @Param []
     * @return com.tanyinghao.comm.result.Result<java.util.List<RecentCommentVO>>
     **/
    @ApiOperation(value = "查看最新评论")
    @GetMapping("/recent/comment")
    public Result<List<RecentCommentVO>> listRecentCommentVO() {
        return Result.success(commentService.listRecentCommentVO());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看评论
     * @Date 16:31 2024/6/14
     * @Param [condition]
     * @return com.tanyinghao.comm.result.Result<PageResult<CommentVO>>
     **/
    @ApiOperation(value = "查看评论")
    @GetMapping("/comment/list")
    public Result<PageResult<CommentVO>> listCommentVO(ConditionDTO condition) {
        return Result.success(commentService.listCommentVO(condition));
    }

    /**
    *
    * @Author TanYingHao
    * @Description 查看回复评论
    * @Date 16:35 2024/6/14
    * @Param [commentId]
    * @return com.tanyinghao.comm.result.Result<java.util.List<com.tanyinghao.model.vo.ReplyVO>>
    **/
    @ApiOperation(value = "查看回复评论")
    @GetMapping("/comment/{commentId}/reply")
    public Result<List<ReplyVO>> listReply(@PathVariable("commentId") Integer commentId) {
        return Result.success(commentService.listReply(commentId));
    }
}
