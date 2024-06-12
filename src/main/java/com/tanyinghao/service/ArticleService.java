package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.*;
import com.tanyinghao.model.entity.Article;
import com.tanyinghao.model.vo.ArticleBackVO;
import com.tanyinghao.model.vo.ArticleInfoVO;
import com.tanyinghao.model.vo.PageResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 * @Author TanYingHao
 * @Description 文章业务接口
 * @Date 15:54 2024/6/11
 **/
public interface ArticleService extends IService<Article> {

    /**
     *
     * @Author TanYingHao
     * @Description 查看后台文章列表
     * @Date 16:06 2024/6/11
     * @Param [condition]
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.ArticleBackVO>
     **/
    PageResult<ArticleBackVO> listArticleBackVO(ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 添加文章
     * @Date 16:07 2024/6/11
     * @Param [article]
     **/
    void addArticle(ArticleDTO article);

    /**
     *
     * @Author TanYingHao
     * @Description 删除文章
     * @Date 16:08 2024/6/11
     * @Param [articleIdList]
     **/
    void deleteArticle(List<Integer> articleIdList);

    /**
     *
     * @Author TanYingHao
     * @Description 回收或者恢复文章
     * @Date 16:09 2024/6/11
     * @Param [delete]
     **/
    void updateArticleDelete(DeleteDTO delete);

    /**
     *
     * @Author TanYingHao
     * @Description 修改文章
     * @Date 16:10 2024/6/11
     * @Param [article]
     * @return void
     **/
    void updateArticle(ArticleDTO article);

    /**
     *
     * @Author TanYingHao
     * @Description 编辑文章
     * @Date 16:11 2024/6/11
     * @Param [articleId]
     * @return com.tanyinghao.model.vo.ArticleInfoVO
     **/
    ArticleInfoVO editArticle(Integer articleId);

    /**
     *
     * @Author TanYingHao
     * @Description 上传文章图片
     * @Date 10:49 2024/6/12
     * @Param [file]
     * @return java.lang.String
     **/
    String saveArticleImages(MultipartFile file);

    /**
     *
     * @Author TanYingHao
     * @Description 置顶文章
     * @Date 10:50 2024/6/12
     * @Param [top]
     **/
    void updateArticleTop(TopDTO top);

    /**
     *
     * @Author TanYingHao
     * @Description 推荐文章
     * @Date 10:51 2024/6/12
     * @Param [recommend]
     **/
    void updateArticleRecommend(RecommendDTO recommend);
}
