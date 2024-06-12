package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.entity.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper extends BaseMapper<Tag> {

    /**
     *
     * @Author TanYingHao
     * @Description 查询已存在的tag
     * @Date 14:44 2024/6/12
     * @Param [tagNameList] 标签名列表
     * @return java.util.List<com.tanyinghao.model.entity.Tag>
     **/
    List<Tag> selectTagList(List<String> tagNameList);

    /**
     *
     * @Author TanYingHao
     * @Description 查询文章标签名称
     * @Date 15:35 2024/6/12
     * @Param [articleId]
     * @return java.util.List<java.lang.String>
     **/
    List<String> selectTagNameByArticleId(@Param("articleId") Integer articleId);
}
