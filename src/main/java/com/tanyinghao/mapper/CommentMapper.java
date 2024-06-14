package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.Comment;
import com.tanyinghao.model.vo.CommentBackVO;
import com.tanyinghao.model.vo.CommentCountVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     *
     * @Author TanYingHao
     * @Description 查看评论数
     * @Date 12:06 2024/6/14
     * @Param [talkIdList, type] id集合，查看的类型（说说、文章、友链）
     * @return java.util.List<com.tanyinghao.model.vo.CommentCountVO>
     **/
    List<CommentCountVO> selectCommentCountByTypeId(@Param("typeIdList") List<Integer> typeIdList, @Param("commentType") Integer commentType);

    /**
     *
     * @Author TanYingHao
     * @Description 根据条件查询评论数
     * @Date 14:13 2024/6/14
     * @Param [condition]
     * @return java.lang.Long
     **/
    Long countComment(@Param("condition") ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 分页查询评论
     * @Date 14:15 2024/6/14
     * @Param [limit, size, condition]
     * @return java.util.List<com.tanyinghao.model.vo.CommentBackVO>
     **/
    List<CommentBackVO> listCommentBackVO(@Param("limit") Long limit, @Param("size") Long size, @Param("condition") ConditionDTO condition);
}


