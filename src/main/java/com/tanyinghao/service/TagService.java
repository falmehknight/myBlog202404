package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.TagDTO;
import com.tanyinghao.model.entity.Tag;
import com.tanyinghao.model.vo.*;

import java.util.List;

public interface TagService extends IService<Tag> {

    /**
     *
     * @Author TanYingHao
     * @Description 查看后台标签
     * @Date 23:09 2024/6/12
     * @Param [condition] 条件
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.TagBackVO>
     **/
    PageResult<TagBackVO> listTagBackVO(ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 增加标签
     * @Date 23:10 2024/6/12
     * @Param [tag]
     **/
    void addTag(TagDTO tag);

    /**
     *
     * @Author TanYingHao
     * @Description 删除标签
     * @Date 23:12 2024/6/12
     * @Param [tagIdList] 标签id集合
     **/
    void deleteTag(List<Integer> tagIdList);

    /**
     *
     * @Author TanYingHao
     * @Description 修改标签
     * @Date 23:13 2024/6/12
     * @Param [tag]
     **/
    void updateTag(TagDTO tag);

    /**
     *
     * @Author TanYingHao
     * @Description 查看标签列表
     * @Date 23:16 2024/6/12
     * @Param []
     * @return java.lang.Object
     **/
    List<TagOptionVO> listTagOption();

    /**
     *
     * @Author TanYingHao
     * @Description 查看标签列表
     * @Date 23:41 2024/6/12
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.TagVO>
     **/
    List<TagVO> listTagVO();

    /**
     *
     * @Author TanYingHao
     * @Description 查看标签下的文章
     * @Date 23:43 2024/6/12
     * @Param [condition]
     * @return java.lang.Object
     **/
    ArticleConditionList listArticleTag(ConditionDTO condition);
}
