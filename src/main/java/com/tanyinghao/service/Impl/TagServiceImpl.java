package com.tanyinghao.service.Impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.comm.utils.PageUtils;
import com.tanyinghao.mapper.ArticleMapper;
import com.tanyinghao.mapper.ArticleTagMapper;
import com.tanyinghao.mapper.TagMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.TagDTO;
import com.tanyinghao.model.entity.ArticleTag;
import com.tanyinghao.model.entity.Tag;
import com.tanyinghao.model.vo.*;
import com.tanyinghao.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName TagServiceImpl
 * @Description 标签服务接口
 * @Author 谭颍豪
 * @Date 2024/6/12 14:53
 * @Version 1.0
 **/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public PageResult<TagBackVO> listTagBackVO(ConditionDTO condition) {
        // 查询标签数
        Long count = tagMapper.selectCount(new LambdaQueryWrapper<Tag>().like(StringUtils.hasText(condition.getKeyword()),
                Tag::getTagName, condition.getKeyword()));
        if (count == 0) {
            return new PageResult<>();
        }
        List<TagBackVO> tagBackVOList = tagMapper.selectTagBackVO(PageUtils.getLimit(), PageUtils.getSize(), condition.getKeyword());
        return new PageResult<>(tagBackVOList, count);
    }

    @Override
    public void addTag(TagDTO tag) {
        Tag existTag = tagMapper.selectOne(new LambdaQueryWrapper<Tag>()
                .select(Tag::getId).eq(Tag::getTagName, tag.getTagName()));
        Assert.isNull(existTag, tag.getTagName() + "标签已存在");
        // 添加新标签
        Tag newTag = Tag.builder()
                .tagName(tag.getTagName())
                .build();
        baseMapper.insert(newTag);
    }

    @Override
    public void deleteTag(List<Integer> tagIdList) {
        // 标签下是否有文章
        Long count = articleTagMapper.selectCount(new LambdaQueryWrapper<ArticleTag>()
                .in(ArticleTag::getTagId, tagIdList));
        Assert.isFalse(count > 0, "标签下有文章，删除失败");
        // 批量删除
        tagMapper.deleteBatchIds(tagIdList);
    }

    @Override
    public void updateTag(TagDTO tag) {
        Tag existTag = tagMapper.selectOne(new LambdaQueryWrapper<Tag>()
                .select(Tag::getId)
                .eq(Tag::getTagName, tag.getTagName()));
        Assert.isFalse(Objects.nonNull(existTag) && !existTag.getId().equals(tag.getId()),
                tag.getTagName() + "标签已存在");
        // 修改标签
        Tag newTag = Tag.builder()
                .id(tag.getId())
                .tagName(tag.getTagName())
                .build();
        baseMapper.updateById(newTag);
    }

    @Override
    public List<TagOptionVO> listTagOption() {
        return tagMapper.selectTagOptionList();
    }

    @Override
    public List<TagVO> listTagVO() {
        return tagMapper.selectTagVOList();
    }

    @Override
    public ArticleConditionList listArticleTag(ConditionDTO condition) {
        List<ArticleConditionVO> articleConditionVOList = articleMapper.listArticleByCondition(PageUtils.getLimit(),
                PageUtils.getSize(), condition);
        Tag tag = tagMapper.selectOne(new LambdaQueryWrapper<Tag>()
                .select(Tag::getTagName).eq(Tag::getId, condition.getTagId()));
        return ArticleConditionList.builder()
                .articleConditionVOList(articleConditionVOList)
                .name(tag.getTagName())
                .build();
    }
}
