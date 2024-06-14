package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.entity.Comment;
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
}
