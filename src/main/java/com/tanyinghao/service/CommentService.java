package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.CheckDTO;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.Comment;
import com.tanyinghao.model.vo.CommentBackVO;
import com.tanyinghao.model.vo.PageResult;

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
}
