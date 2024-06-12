package com.tanyinghao.strategy.context;

import com.tanyinghao.comm.enums.SearchModeEnum;
import com.tanyinghao.model.vo.ArticleSearchVO;
import com.tanyinghao.strategy.SearchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SearchStrategyContext
 * @Description 搜索策略上下文
 * @Author 谭颍豪
 * @Date 2024/6/12 17:10
 * @Version 1.0
 **/
@Service
public class SearchStrategyContext {

    @Value("${search.mode}")
    private String searchMode;

    @Autowired
    private Map<String, SearchStrategy> searchStrategyMap;

    public List<ArticleSearchVO> executeSearchStrategy(String keyword) {
        return searchStrategyMap.get(SearchModeEnum.getStrategy(searchMode)).searchArticle(keyword);
    }


}
