package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.entity.ArticleTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    /**
     *
     * @Author TanYingHao
     * @Description 批量保存标签到对应的文章中
     * @Date 15:01 2024/6/12
     * @Param [articleId, existTagIdList]
     * @return void
     **/
    void saveBatchArticleTag(@Param("articleId") Integer articleId, List<Integer> existTagIdList);
}
