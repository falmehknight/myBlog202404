package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.CheckDTO;
import com.tanyinghao.model.dto.CommentDTO;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.Comment;
import com.tanyinghao.model.vo.*;

import java.util.List;

public interface CommentService extends IService<Comment> {

    /**
     *
     * @Author TanYingHao
     * @Description 查看后台留言列表
     * @Date 14:11 2024/6/14
     * @Param [condition]
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.CommentBackVO>
     **/
    PageResult<CommentBackVO> listCommentBackVO(ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 审核留言
     * @Date 14:11 2024/6/14
     * @Param [check]
     **/
    void updateCommentCheck(CheckDTO check);

    /**
     *
     * @Author TanYingHao
     * @Description 添加评论
     * @Date 16:20 2024/6/14
     * @Param [comment]
     **/
    void addComment(CommentDTO comment);

    /**
     *
     * @Author TanYingHao
     * @Description 查看最新的评论
     * @Date 16:31 2024/6/14
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.RecentCommentVO>
     **/
    List<RecentCommentVO> listRecentCommentVO();

    /**
     *
     * @Author TanYingHao
     * @Description 查看评论
     * @Date 16:34 2024/6/14
     * @Param [condition]
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.CommentVO>
     **/
    PageResult<CommentVO> listCommentVO(ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 查看回复评论
     * @Date 16:35 2024/6/14
     * @Param [commentId]
     * @return java.util.List<com.tanyinghao.model.vo.ReplyVO>
     **/
    List<ReplyVO> listReply(Integer commentId);
}
