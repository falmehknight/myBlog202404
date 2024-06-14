package com.tanyinghao.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.comm.utils.PageUtils;
import com.tanyinghao.mapper.CommentMapper;
import com.tanyinghao.model.dto.CheckDTO;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.Comment;
import com.tanyinghao.model.vo.CommentBackVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName CommentServiceImpl
 * @Description 评论服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/14 14:06
 * @Version 1.0
 **/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public PageResult<CommentBackVO> listCommentBackVO(ConditionDTO condition) {
        // 查询后台评论数量
        Long count = commentMapper.countComment(condition);
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询后台评论集合
        List<CommentBackVO> commentBackVOList = commentMapper.listCommentBackVO(PageUtils.getLimit(),
                PageUtils.getSize(), condition);
        return new PageResult<>(commentBackVOList, count);
    }

    @Override
    public void updateCommentCheck(CheckDTO check) {
        List<Comment> commentList = check.getIdList()
                .stream()
                .map(id -> Comment.builder().id(id).isCheck(check.getIsCheck()).build()).collect(Collectors.toList());
        this.updateBatchById(commentList);
    }
}
