package com.tanyinghao.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.comm.utils.BeanCopyUtils;
import com.tanyinghao.comm.utils.FileUtils;
import com.tanyinghao.comm.utils.PageUtils;
import com.tanyinghao.mapper.*;
import com.tanyinghao.model.dto.*;
import com.tanyinghao.model.entity.*;
import com.tanyinghao.model.vo.ArticleBackVO;
import com.tanyinghao.model.vo.ArticleInfoVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.service.ArticleService;
import com.tanyinghao.service.RedisService;
import com.tanyinghao.service.TagService;
import com.tanyinghao.strategy.context.UploadStrategyContext;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tanyinghao.comm.constant.CommConstant.FALSE;
import static com.tanyinghao.comm.constant.RedisConstant.*;
import static com.tanyinghao.comm.enums.FilePathEnum.ARTICLE;

/**
 * @ClassName ArticleServiceImpl
 * @Description 文章服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/11 16:00
 * @Version 1.0
 **/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Autowired
    private BlogFileMapper blogFileMapper;

    @Override
    public PageResult<ArticleBackVO> listArticleBackVO(ConditionDTO condition) {
        // 查询文章数量
        Long count = articleMapper.countArticleBackVO(condition);
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询文章后台信息
        List<ArticleBackVO> articleBackVOList = articleMapper.selectArticleBackVO(PageUtils.getLimit(), PageUtils.getSize(), condition);
        // 浏览量
        Map<Object, Double> viewCountMap = redisService.getZsetAllScore(ARTICLE_VIEW_COUNT);
        // 点赞量
        Map<String, Integer> likeCountMap = redisService.getHashAll(ARTICLE_LIKE_COUNT);
        // 封装文章后台信息
        articleBackVOList.forEach(item -> {
            Double viewCount = Optional.ofNullable(viewCountMap.get(item.getId())).orElse((double) 0);
            item.setViewCount(viewCount.intValue());
            Integer likeCount = Optional.ofNullable(likeCountMap.get(item.getId().toString())).orElse(0);
            item.setLikeCount(likeCount);
        });
        return new PageResult<>(articleBackVOList, count);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addArticle(ArticleDTO article) {
        // 保存文章分类
        Integer categoryId = saveArticleCategory(article);
        Article newArticle = BeanCopyUtils.copyBean(article, Article.class);
        // 如果缩略图没有的，给他设上默认的
        if (StringUtils.isBlank(newArticle.getArticleCover())) {
            SiteConfig siteConfig = redisService.getObject(SITE_SETTING);
            newArticle.setArticleCover(siteConfig.getArticleCover());
        }
        newArticle.setCategoryId(categoryId);
        newArticle.setUserId(StpUtil.getLoginIdAsInt());
        baseMapper.insert(newArticle);
        // 保存文章标签
        saveArticleTag(article, newArticle.getId());
    }
    
    /**
     *
     * @Author TanYingHao
     * @Description 保存文章标签 
     * @Date 14:34 2024/6/12
     * @Param [article, id]
     **/
    private void saveArticleTag(ArticleDTO article, Integer articleId) {
        // 删除id文章的标签
        articleTagMapper.delete(new LambdaQueryWrapper<ArticleTag>()
                .eq(ArticleTag::getArticleId, articleId)
        );
        // 获取标签名集合
        List<String> tagNameList = article.getTagNameList();
        if (CollectionUtils.isNotEmpty(tagNameList)) {
            // 查询已存在的标签
            List<Tag> existTagList = tagMapper.selectTagList(tagNameList);
            List<String> existTagNameList = existTagList.stream()
                    .map(Tag::getTagName)
                    .collect(Collectors.toList());
            List<Integer> existTagIdList = existTagList.stream()
                    .map(Tag::getId)
                    .collect(Collectors.toList());
            // 删除已存在的标签列表
            tagNameList.removeAll(existTagNameList);
            // 含有新标签
            if (CollectionUtils.isNotEmpty(tagNameList)) {
                // 新标签列表
                List<Tag> newTagList = tagNameList.stream()
                        .map(item -> Tag.builder().tagName(item).build())
                        .collect(Collectors.toList());
                // 保存新标签
                tagService.saveBatch(newTagList);
                // 获取新标签id列表
                List<Integer> newTagIdList = newTagList.stream()
                        .map(Tag::getId)
                        .collect(Collectors.toList());
                // 将新标签保存一下
                existTagIdList.addAll(newTagIdList);
            }
            // 将所有标签绑定到文章标签关联表中
            articleTagMapper.saveBatchArticleTag(articleId, existTagIdList);
        }

    }
    
    /**
     *
     * @Author TanYingHao
     * @Description 保存文章分类
     * @Date 14:34 2024/6/12
     * @Param [article]
     * @return java.lang.Integer
     **/
    private Integer saveArticleCategory(ArticleDTO article) {
        //  查询是否存在该分类
        Category category = categoryMapper.selectOne(new LambdaQueryWrapper<Category>()
                .select(Category::getId)
                .eq(Category::getCategoryName, article.getCategoryName()));
        if (Objects.isNull(category)) {
            // 不存在则新建并插入
            category = Category.builder().categoryName(article.getCategoryName()).build();
            categoryMapper.insert(category);
        }
        return category.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteArticle(List<Integer> articleIdList) {
        // 删除文章标签
        articleTagMapper.delete(new LambdaQueryWrapper<ArticleTag>()
                .in(ArticleTag::getArticleId, articleIdList));
        // 删除文章
        articleMapper.deleteBatchIds(articleIdList);
    }

    @Override
    public void updateArticleDelete(DeleteDTO delete) {
        // 批量更新问斩删除状态
        List<Article> articleList = delete.getIdList()
                .stream()
                .map(id -> Article.builder()
                        .id(id)
                        .isDelete(delete.getIsDelete())
                        .isTop(FALSE)
                        .isRecommend(FALSE)
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(articleList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateArticle(ArticleDTO article) {
        // 保存文章分类
        Integer categoryId = saveArticleCategory(article);
        // 修改文章
        Article newArticle = BeanCopyUtils.copyBean(article, Article.class);
        newArticle.setUserId(StpUtil.getLoginIdAsInt());
        newArticle.setCategoryId(categoryId);
        baseMapper.updateById(newArticle);
        // 保存文章标签
        saveArticleTag(article, newArticle.getId());
    }

    @Override
    public ArticleInfoVO editArticle(Integer articleId) {
        // 查询文章信息
        ArticleInfoVO articleInfoVO = articleMapper.selectArticleInfoById(articleId);
        Assert.notNull(articleInfoVO, "不存在该文章");
        // 查询文章分类名称
        Category category = categoryMapper.selectOne(new LambdaQueryWrapper<Category>()
                .select(Category::getCategoryName)
                .eq(Category::getId, articleInfoVO.getCategoryId()));
        // 查询文章标签名称
        List<String> tagNameList = tagMapper.selectTagNameByArticleId(articleId);
        articleInfoVO.setCategoryName(category.getCategoryName());
        articleInfoVO.setTagNameList(tagNameList);
        return articleInfoVO;
    }

    @Override
    public String saveArticleImages(MultipartFile file) {
        // 上传文件
        String url = uploadStrategyContext.executeUploadStrategy(file, ARTICLE.getPath());
        try {
            // 获取md5以及文件扩展名
            String md5 = FileUtils.getMd5(file.getInputStream());
            String extension = FileUtils.getExtension(file);
            BlogFile existFile = blogFileMapper.selectOne(new LambdaQueryWrapper<BlogFile>()
                    .select(BlogFile::getId)
                    .eq(BlogFile::getFileName, md5)
                    .eq(BlogFile::getFilePath, ARTICLE.getFilePath()));
            // 不存在则保存新的
            if (Objects.isNull(existFile)) {
                BlogFile newFile = BlogFile.builder()
                        .fileUrl(url)
                        .fileName(md5)
                        .filePath(ARTICLE.getFilePath())
                        .extendName(extension)
                        .fileSize((int) file.getSize())
                        .isDir(FALSE)
                        .build();
                blogFileMapper.insert(newFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public void updateArticleTop(TopDTO top) {
        Article article = Article.builder()
                .id(top.getId())
                .isTop(top.getIsTop())
                .build();
        articleMapper.updateById(article);
    }

    @Override
    public void updateArticleRecommend(RecommendDTO recommend) {
        Article article = Article.builder()
                .id(recommend.getId())
                .isRecommend(recommend.getIsRecommend())
                .build();
        articleMapper.updateById(article);
    }
}
