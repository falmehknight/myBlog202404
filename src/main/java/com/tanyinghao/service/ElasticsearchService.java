package com.tanyinghao.service;

import com.tanyinghao.model.vo.ArticleSearchVO;

// es文章业务接口
public interface ElasticsearchService {
    
    /**
     *
     * @Author TanYingHao
     * @Description 添加文章 
     * @Date 11:06 2024/6/17
     * @Param [article]
     **/
    void addArticle(ArticleSearchVO article);
    
    /**
     *
     * @Author TanYingHao
     * @Description 更新文章
     * @Date 11:07 2024/6/17
     * @Param [article]
     **/
    void updateArticle(ArticleSearchVO article);

    /**
     *
     * @Author TanYingHao
     * @Description 删除文章
     * @Date 11:07 2024/6/17
     * @Param [id] 文章id
     **/
    void deleteArticle(Integer id);
}
