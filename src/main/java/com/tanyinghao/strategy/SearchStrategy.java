package com.tanyinghao.strategy;


import com.tanyinghao.model.vo.ArticleSearchVO;

import java.util.List;

public interface SearchStrategy {

    /**
     *
     * @Author TanYingHao
     * @Description 搜索文章
     * @Date 17:31 2024/6/12
     * @Param [keyword] 关键字
     * @return java.util.List<com.tanyinghao.model.vo.ArticleSearchVO> 文章列表
     **/
    List<ArticleSearchVO> searchArticle(String keyword);
}
