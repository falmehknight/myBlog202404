package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.Article;
import com.tanyinghao.model.vo.ArticleBackVO;
import com.tanyinghao.model.vo.ArticleInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {
    
    /**
     *
     * @Author TanYingHao
     * @Description 查询文章数量
     * @Date 10:56 2024/6/12
     * @Param [condition]
     * @return java.lang.Long
     **/
    Long countArticleBackVO(@Param("condition") ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 查询文章后台信息
     * @Date 11:19 2024/6/12
     * @Param [limit, size, condition]
     * @return java.util.List<com.tanyinghao.model.vo.ArticleBackVO>
     **/
    List<ArticleBackVO> selectArticleBackVO(@Param("limit") Long limit, @Param("size") Long size, @Param("condition") ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 查询文章信息
     * @Date 15:29 2024/6/12
     * @Param [articleId] 文章id
     * @return com.tanyinghao.model.vo.ArticleInfoVO
     **/
    @Select("select id, category_id, article_cover, article_title, article_content, article_type, is_top, is_recommend," +
            "`status` from t_article where id = #{articleId}")
    ArticleInfoVO selectArticleInfoById(@Param("articleId")  Integer articleId);
}
