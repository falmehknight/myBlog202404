package com.tanyinghao.service.Impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import com.tanyinghao.model.vo.ArticleSearchVO;
import com.tanyinghao.service.ElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.tanyinghao.comm.constant.ElasticConstant.ARTICLE_INDEX;

/**
 * @ClassName ElasticsearchServiceImpl
 * @Description es文章业务接口实现类
 * @Author 谭颍豪
 * @Date 2024/6/17 11:08
 * @Version 1.0
 **/
@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Override
    public void addArticle(ArticleSearchVO article) {
        try {
            IndexRequest<ArticleSearchVO> indexRequest = IndexRequest.of(request -> request
                    .index(ARTICLE_INDEX)
                    .id(article.getId().toString())
                    .document(article));
            elasticsearchClient.index(indexRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateArticle(ArticleSearchVO article) {
        try {
            elasticsearchClient.update(request -> request
                    .index(ARTICLE_INDEX)
                    .id(article.getId().toString())
                    .doc(article), ArticleSearchVO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteArticle(Integer id) {
        try {
            elasticsearchClient.delete(
                    deleteRequest -> deleteRequest
                            .index(ARTICLE_INDEX)
                            .id(id.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
