package com.tanyinghao.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tanyinghao.comm.annotation.OptLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.CheckDTO;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.vo.CommentBackVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tanyinghao.comm.constant.OptTypeConstant.DELETE;
import static com.tanyinghao.comm.constant.OptTypeConstant.UPDATE;

/**
 * @ClassName CommentController
 * @Description 评论模块控制器
 * @Author 谭颍豪
 * @Date 2024/6/14 14:04
 * @Version 1.0
 **/
@Api(tags = "后台评论管理模块")
@RestController
@RequestMapping("/admin/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

   /**
    *
    * @Author TanYingHao
    * @Description 查看后台评论列表
    * @Date 14:08 2024/6/14
    * @Param [condition]
    * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.CommentBackVO>>
    **/
    @ApiOperation(value = "查看后台评论")
    @SaCheckPermission("news:comment:list")
    @GetMapping("/list")
    public Result<PageResult<CommentBackVO>> listCommentBackVO(ConditionDTO condition) {
        return Result.success(commentService.listCommentBackVO(condition));
    }


    /**
     *
     * @Author TanYingHao
     * @Description 删除评论
     * @Date 14:09 2024/6/14
     * @Param [commentIdList]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = DELETE)
    @ApiOperation(value = "删除评论")
    @SaCheckPermission("news:comment:delete")
    @DeleteMapping("/delete")
    public Result<?> deleteComment(@RequestBody List<Integer> commentIdList) {
        commentService.removeByIds(commentIdList);
        return Result.success();
    }

   /**
    *
    * @Author TanYingHao
    * @Description 审核评论
    * @Date 14:10 2024/6/14
    * @Param [check]
    * @return com.tanyinghao.comm.result.Result<?>
    **/
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "审核评论")
    @SaCheckPermission("news:comment:pass")
    @PutMapping("/pass")
    public Result<?> updateCommentCheck(@Validated @RequestBody CheckDTO check) {
        commentService.updateCommentCheck(check);
        return Result.success();
    }

}
