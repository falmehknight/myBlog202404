package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.Article;
import com.tanyinghao.model.vo.*;
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

    /**
     *
     * @Author TanYingHao
     * @Description 查询首页文章
     * @Date 16:29 2024/6/12
     * @Param [limit, limit1]
     * @return java.util.List<com.tanyinghao.model.vo.ArticleHomeVO>
     **/
    List<ArticleHomeVO> selectArticleHomeList(@Param("limit") Long limit, @Param("size") Long size);

    /**
     *
     * @Author TanYingHao
     * @Description 根据文章id查询文章
     * @Date 16:33 2024/6/12
     * @Param [articleId]
     * @return com.tanyinghao.model.vo.ArticleVO
     **/
    ArticleVO selectArticleHomeById(@Param("articleId") Integer articleId);

    /**
     *
     * @Author TanYingHao
     * @Description 查询上一篇文章
     * @Date 16:38 2024/6/12
     * @Param [articleId]
     * @return com.tanyinghao.model.vo.ArticlePaginationVO
     **/
    @Select("SELECT id, article_cover, article_title FROM t_article WHERE is_delete = 0 AND `status` = 1 " +
            "AND id < #{articleId} ORDER BY id DESC LIMIT 1")
    ArticlePaginationVO selectLastArticle(@Param("articleId") Integer articleId);

    /**
     *
     * @Author TanYingHao
     * @Description 查询下一篇文章
     * @Date 16:42 2024/6/12
     * @Param [articleId]
     * @return com.tanyinghao.model.vo.ArticlePaginationVO
     **/
    @Select("SELECT id, article_cover, article_title FROM t_article WHERE is_delete = 0 AND `status` = 1 " +
            "AND id > #{articleId} ORDER BY id LIMIT 1")
    ArticlePaginationVO selectNextArticle(@Param("articleId") Integer articleId);

    /**
     *
     * @Author TanYingHao
     * @Description 查询推荐文章
     * @Date 16:48 2024/6/12
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.ArticleRecommendVO>
     **/
    @Select("SELECT id, article_title, article_cover, create_time FROM t_article WHERE is_delete = 0 AND `status` = 1 "
            + "AND is_recommend =  1 LIMIT 5")
    List<ArticleRecommendVO> selectArticleRecommend();

    /**
     *
     * @Author TanYingHao
     * @Description 查询归档文件
     * @Date 16:53 2024/6/12
     * @Param [limit, limit1]
     * @return java.util.List<com.tanyinghao.model.vo.ArchiveVO>
     **/
    @Select("SELECT id, article_title, article_cover, create_time FROM t_article WHERE is_delete = 0 AND `status` = 1 "
            + "ORDER BY create_time limit #{limit}, ${size}")
    List<ArchiveVO> selectArchiveList(@Param("limit") Long limit, @Param("size") Long size);
}
