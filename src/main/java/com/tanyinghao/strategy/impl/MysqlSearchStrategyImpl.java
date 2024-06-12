package com.tanyinghao.strategy.impl;

import com.tanyinghao.mapper.ArticleMapper;
import com.tanyinghao.model.vo.ArticleSearchVO;
import com.tanyinghao.strategy.SearchStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tanyinghao.comm.constant.ElasticConstant.POST_TAG;
import static com.tanyinghao.comm.constant.ElasticConstant.PRE_TAG;

/**
 * @ClassName MysqlSearchStrategyImpl
 * @Description mysql搜索策略
 * @Author 谭颍豪
 * @Date 2024/6/12 17:37
 * @Version 1.0
 **/
@Service("mySqlSearchStrategyImpl")
public class MysqlSearchStrategyImpl implements SearchStrategy {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public List<ArticleSearchVO> searchArticle(String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return new ArrayList<>();
        }
        List<ArticleSearchVO> articleSearchVOList = articleMapper.searchArticle(keyword);
        return articleSearchVOList.stream()
                .map(article -> {
                    // 获取关键词第一次出现的位置
                    String articleContent = article.getArticleContent();
                    int index = articleContent.indexOf(keyword);
                    if (index != -1) {
                        // 获取关键词前面的文字
                        int preIndex = index > 25 ? index - 25 : 0;
                        String preText = articleContent.substring(preIndex, index);
                        // 获取关键词后面的文字
                        int last = index + keyword.length();
                        int postLength = articleContent.length() - last;
                        int postIndex = postLength > 175 ? last + 175: last + postLength;
                        String postText = articleContent.substring(index, postIndex);
                        // 文章内容高亮
                        articleContent = (preText + postText).replaceAll(keyword, PRE_TAG + keyword + POST_TAG);
                    }
                    String articleTitle = article.getArticleTitle()
                            .replaceAll(keyword, PRE_TAG + keyword + POST_TAG);
                    return ArticleSearchVO.builder()
                            .id(article.getId())
                            .articleTitle(articleTitle)
                            .articleContent(articleContent)
                            .build();
                })
                .collect(Collectors.toList());
    }
}