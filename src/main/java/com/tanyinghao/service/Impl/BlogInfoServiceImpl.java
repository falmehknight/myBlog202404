package com.tanyinghao.service.Impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.tanyinghao.comm.utils.IpUtils;
import com.tanyinghao.comm.utils.UserAgentUtils;
import com.tanyinghao.mapper.*;
import com.tanyinghao.model.entity.Article;
import com.tanyinghao.model.entity.SiteConfig;
import com.tanyinghao.model.vo.*;
import com.tanyinghao.service.BlogInfoService;
import com.tanyinghao.service.RedisService;
import com.tanyinghao.service.SiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static com.tanyinghao.comm.constant.CommConstant.FALSE;
import static com.tanyinghao.comm.constant.RedisConstant.*;
import static com.tanyinghao.comm.enums.ArticleStatusEnum.PUBLIC;

/**
 * @ClassName BlogInfoServiceImpl
 * @Description 博客信息服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/15 18:32
 * @Version 1.0
 **/
public class BlogInfoServiceImpl implements BlogInfoService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private SiteConfigService siteConfigService;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VisitLogMapper visitLogMapper;

    @Override
    public void report() {
        // 获取用户ip
        String ipAddress = IpUtils.getIpAddress(request);
        Map<String, String> userAgentMap = UserAgentUtils.parseOsAndBrowser(request.getHeader("User-Agent"));
        // 获取访问设备
        String browser = userAgentMap.get("browser");
        String os = userAgentMap.get("os");
        // 生成唯一用户标识
        String uuid = ipAddress + browser + os;
        String md5 = DigestUtils.md5DigestAsHex(uuid.getBytes());
        // 判断是否访问
        if (!redisService.hasSetValue(UNIQUE_VISITOR, md5)) {
            // 访问量+1
            redisService.incr(BLOG_VIEW_COUNT, 1);
            // 保存唯一标识
            redisService.setSet(UNIQUE_VISITOR, md5);
        }
    }

    @Override
    public BlogInfoVO getBlogInfo() {
        // 文章数量
        Long articleCount = articleMapper.selectCount(new LambdaQueryWrapper<Article>()
                .eq(Article::getStatus, PUBLIC.getStatus()).eq(Article::getIsDelete, FALSE));
        // 分类数量
        Long categoryCount = categoryMapper.selectCount(null);
        // 标签数量
        Long tagCount = tagMapper.selectCount(null);
        // 博客访问量
        Integer count = redisService.getObject(BLOG_VIEW_COUNT);
        String viewCount = Optional.ofNullable(count).orElse(0).toString();
        // 网站配置
        SiteConfig siteConfig = siteConfigService.getSiteConfig();
        return BlogInfoVO.builder()
                .articleCount(articleCount)
                .categoryCount(categoryCount)
                .tagCount(tagCount)
                .viewCount(viewCount)
                .siteConfig(siteConfig)
                .build();
    }

    @Override
    public BlogBackInfoVO getBlogBackInfo() {
        // 访问量
        Integer viewCount = redisService.getObject(BLOG_VIEW_COUNT);
        // 留言量
        Long messageCount = messageMapper.selectCount(null);
        // 用户量
        Long userCount = userMapper.selectCount(null);
        // 文章量
        Long articleCount = articleMapper.selectCount(new LambdaQueryWrapper<Article>()
                .eq(Article::getIsDelete, FALSE));
        // 分类数据
        List<CategoryVO> categoryVOList = categoryMapper.selectCategoryVO();
        // 标签数据
        List<TagOptionVO> tagVOList = tagMapper.selectTagOptionList();
        // 查询用户浏览
        DateTime startTime = DateUtil.beginOfDay(DateUtil.offsetDay(new Date(), -7));
        DateTime endTime = DateUtil.endOfDay(new Date());
        List<UserViewVO> userViewVOList = visitLogMapper.selectUserViewList(startTime, endTime);
        // 文章统计
        List<ArticleStatisticsVO> articleStatisticsList = articleMapper.selectArticleStatistics();
        // 查询redis访问量前五的文章
        Map<Object, Double> articleMap = redisService.zReverseRangeWithScore(ARTICLE_VIEW_COUNT, 0, 4);
        BlogBackInfoVO blogBackInfoVO = BlogBackInfoVO.builder()
                .articleStatisticsList(articleStatisticsList)
                .tagVOList(tagVOList)
                .viewCount(viewCount)
                .messageCount(messageCount)
                .userCount(userCount)
                .articleCount(articleCount)
                .categoryVOList(categoryVOList)
                .userViewVOList(userViewVOList)
                .build();
        if (CollectionUtils.isNotEmpty(articleMap)) {
            // 查询文章排行
            List<ArticleRankVO> articleRankVOList = listArticleRank(articleMap);
            blogBackInfoVO.setArticleRankVOList(articleRankVOList);
        }
        return blogBackInfoVO;
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查找文章排行
     * @Date 18:54 2024/6/15
     * @Param [articleMap]
     * @return java.util.List<com.tanyinghao.model.vo.ArticleRankVO>
     **/
    private List<ArticleRankVO> listArticleRank(Map<Object, Double> articleMap) {
        // 提取文章id
        List<Integer> articleIdList = new ArrayList<>(articleMap.size());
        articleMap.forEach((key, value) -> articleIdList.add((Integer) key));
        // 查询文章信息
        List<Article> articleList = articleMapper.selectList(new LambdaQueryWrapper<Article>()
                .select(Article::getId, Article::getArticleTitle)
                .in(Article::getId, articleIdList));
        return articleList.stream()
                .map(article -> ArticleRankVO.builder()
                        .articleTitle(article.getArticleTitle())
                        .viewCount(articleMap.get(article.getId()).intValue())
                        .build())
                .sorted(Comparator.comparingInt(ArticleRankVO::getViewCount).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public String getAbout() {
        SiteConfig siteConfig = redisService.getObject(SITE_SETTING);
        return siteConfig.getAboutMe();
    }
}
