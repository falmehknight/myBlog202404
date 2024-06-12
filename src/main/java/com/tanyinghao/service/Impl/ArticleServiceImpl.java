package com.tanyinghao.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.mapper.ArticleMapper;
import com.tanyinghao.model.dto.*;
import com.tanyinghao.model.entity.Article;
import com.tanyinghao.model.vo.ArticleBackVO;
import com.tanyinghao.model.vo.ArticleInfoVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName ArticleServiceImpl
 * @Description 文章服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/11 16:00
 * @Version 1.0
 **/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public PageResult<ArticleBackVO> listArticleBackVO(ConditionDTO condition) {
        return null;
    }

    @Override
    public void addArticle(ArticleDTO article) {

    }

    @Override
    public void deleteArticle(List<Integer> articleIdList) {

    }

    @Override
    public void updateArticleDelete(DeleteDTO delete) {

    }

    @Override
    public void updateArticle(ArticleDTO article) {

    }

    @Override
    public ArticleInfoVO editArticle(Integer articleId) {
        return null;
    }

    @Override
    public String saveArticleImages(MultipartFile file) {
        return null;
    }

    @Override
    public void updateArticleTop(TopDTO top) {

    }

    @Override
    public void updateArticleRecommend(RecommendDTO recommend) {

    }
}
