package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.entity.Tag;
import com.tanyinghao.model.vo.TagBackVO;
import com.tanyinghao.model.vo.TagOptionVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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

    /**
     *
     * @Author TanYingHao
     * @Description 分页查询标签列表
     * @Date 23:24 2024/6/12
     * @Param [limit, size, keyword] 页码，大小，关键字
     * @return java.util.List<com.tanyinghao.model.vo.TagBackVO>
     **/
    List<TagBackVO> selectTagBackVO(@Param("limit") Long limit, @Param("size") Long size, @Param("keyword") String keyword);

    /**
     *
     * @Author TanYingHao
     * @Description 查询标签选项
     * @Date 23:36 2024/6/12
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.TagOptionVO>
     **/
    @Select("SELECT id, tag_name FROM t_tag")
    List<TagOptionVO> selectTagOptionList();
}
