package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.Comment;
import com.tanyinghao.model.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
    /**
     *
     * @Author TanYingHao
     * @Description 根据父评论id查询子评论id
     * @Date 18:09 2024/6/14
     * @Param [parentId]
     * @return java.util.List<java.lang.Integer>
     **/
    List<Integer> selectCommentIdByParentId(@Param("parentId") Integer parentId);

    /**
     *
     * @Author TanYingHao
     * @Description 查询最近的评论
     * @Date 0:19 2024/6/15
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.RecentCommentVO>
     **/
    @Select("SELECT c.id, u.nickname, avatar, comment_content, c.create_time FROM t_comment c JOIN t_user u " +
            "ON c.from_uid = u.id WHERE c.is_check = 1 ORDER BY c.create_time DESC LIMIT 5")
    List<RecentCommentVO> selectRecentComment();

    /**
     *
     * @Author TanYingHao
     * @Description 分页查询子评论
     * @Date 0:24 2024/6/15
     * @Param [limit, size, commentId]
     * @return java.util.List<com.tanyinghao.model.vo.ReplyVO>
     **/
    List<ReplyVO> selectReplyByParentId(@Param("limit") Long limit, @Param("size") Long size, @Param("commentId") Integer commentId);

    /**
     *
     * @Author TanYingHao
     * @Description 分页查询父评论
     * @Date 0:31 2024/6/15
     * @Param [limit, size, condition]
     * @return java.util.List<com.tanyinghao.model.vo.CommentVO>
     **/
    List<CommentVO> selectParentComment(@Param("limit") Long limit, @Param("size") Long size, @Param("condition") ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 查询每条父评论下的前三条子评论
     * @Date 0:33 2024/6/15
     * @Param [parentCommentIdList] 父评论id集合
     * @return java.util.List<com.tanyinghao.model.vo.ReplyVO>
     *     这里利用Row_Number() OVER函数实现取前三条
     **/
    List<ReplyVO> selectReplyByParentIdList(@Param("parentCommentIdList") List<Integer> parentCommentIdList);

    /**
     *
     * @Author TanYingHao
     * @Description 查看父评论的回复数
     * @Date 0:41 2024/6/15
     * @Param [parentCommentIdList]
     * @return java.util.List<com.tanyinghao.model.vo.ReplyCountVO>
     **/
    List<ReplyCountVO> selectReplyCountByParentId(@Param("parentCommentIdList") List<Integer> parentCommentIdList);
}


