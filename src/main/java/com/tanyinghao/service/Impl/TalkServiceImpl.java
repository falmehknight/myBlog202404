package com.tanyinghao.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.comm.enums.FilePathEnum;
import com.tanyinghao.comm.utils.*;
import com.tanyinghao.mapper.BlogFileMapper;
import com.tanyinghao.mapper.CommentMapper;
import com.tanyinghao.mapper.TalkMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.TalkDTO;
import com.tanyinghao.model.entity.BlogFile;
import com.tanyinghao.model.entity.Talk;
import com.tanyinghao.model.vo.*;
import com.tanyinghao.service.RedisService;
import com.tanyinghao.service.TalkService;
import com.tanyinghao.strategy.context.UploadStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tanyinghao.comm.constant.CommConstant.FALSE;
import static com.tanyinghao.comm.constant.RedisConstant.TALK_LIKE_COUNT;
import static com.tanyinghao.comm.enums.ArticleStatusEnum.PUBLIC;
import static com.tanyinghao.comm.enums.CommentTypeEnum.TALK;

/**
 * @ClassName TalkServiceImpl
 * @Description 说说服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/14 9:54
 * @Version 1.0
 **/
@Service
public class TalkServiceImpl extends ServiceImpl<TalkMapper, Talk> implements TalkService {

    @Autowired
    private TalkMapper talkMapper;

    @Autowired
    private BlogFileMapper blogFileMapper;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Autowired
    private RedisService redisService;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public PageResult<TalkBackVO> listTalkBackVO(ConditionDTO condition) {
        // 查询说说数量
        Long count = talkMapper.selectCount(new LambdaQueryWrapper<Talk>()
                .eq(Objects.nonNull(condition.getStatus()), Talk::getStatus, condition.getStatus()));
        if (count == 0) {
            return new PageResult<>();
        }
        // 分页查询说说列表
        List<TalkBackVO> talkBackVOList = talkMapper.selectTalkBackVO(PageUtils.getLimit(),
                PageUtils.getSize(), condition.getStatus());
        talkBackVOList.forEach(item -> {
            // 转换图片格式
            if (Objects.nonNull(item.getImages())) {
                item.setImgList(CommUtils.castList(JSON.parseObject(item.getImages(), List.class), String.class));
            }
        });
        return new PageResult<>(talkBackVOList, count);
    }

    @Override
    public String uploadTalkCover(MultipartFile file) {
        // 上传文件
        String url = uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.TALK.getPath());
        try {
            // 获取文件md5值
            String md5 = FileUtils.getMd5(file.getInputStream());
            // 获取文件扩展名
            String extName = FileUtils.getExtension(file);
            BlogFile existFile = blogFileMapper.selectOne(new LambdaQueryWrapper<BlogFile>()
                    .select(BlogFile::getId)
                    .eq(BlogFile::getFileName, md5)
                    .eq(BlogFile::getFilePath, FilePathEnum.TALK.getFilePath()));
            if (Objects.isNull(existFile)) {
                // 保存文件信息
                BlogFile newFile = BlogFile.builder()
                        .fileUrl(url)
                        .fileName(md5)
                        .filePath(FilePathEnum.TALK.getFilePath())
                        .extendName(extName)
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
    public void addTalk(TalkDTO talk) {
        Talk newTalk = BeanCopyUtils.copyBean(talk, Talk.class);
        newTalk.setUserId(StpUtil.getLoginIdAsInt());
        baseMapper.insert(newTalk);
    }

    @Override
    public void deleteTalk(Integer talkId) {
        talkMapper.deleteById(talkId);
    }

    @Override
    public void updateTalk(TalkDTO talk) {
        Talk newTalk = BeanCopyUtils.copyBean(talk, Talk.class);
        newTalk.setUserId(StpUtil.getLoginIdAsInt());
        baseMapper.updateById(newTalk);
    }

    @Override
    public TalkBackInfoVO editTalk(Integer talkId) {
        TalkBackInfoVO talkBackInfoVO = talkMapper.selectTalkBackById(talkId);
        // 转换图片格式
        if (Objects.nonNull(talkBackInfoVO.getImages())) {
            talkBackInfoVO.setImgList(CommUtils.castList(JSON.parseObject(talkBackInfoVO.getImages(), List.class), String.class));
        }
        return talkBackInfoVO;
    }

    @Override
    public List<String> listTalkHome() {
        // 查看首页最新5条说说
        List<Talk> talkList = talkMapper.selectList(new LambdaQueryWrapper<Talk>()
                .select(Talk::getTalkContent)
                .eq(Talk::getStatus, PUBLIC.getStatus())
                .orderByDesc(Talk::getIsTop)
                .orderByDesc(Talk::getId)
                .last("limit 5"));
        return talkList.stream().map(item -> item.getTalkContent().length() > 200
                ? HTMLUtils.deleteHtmlTag(item.getTalkContent().substring(0,200))
                : HTMLUtils.deleteHtmlTag(item.getTalkContent()))
                .collect(Collectors.toList());
    }

    @Override
    public PageResult<TalkVO> listTalkVO() {
        // 查询说说数量
        Long count = talkMapper.selectCount(new LambdaQueryWrapper<Talk>()
                .eq(Talk::getStatus, PUBLIC.getStatus()));
        if (count == 0) {
            return new PageResult<>();
        }
        // 分页查询说说
        List<TalkVO> talkVOList = talkMapper.selectTalkList(PageUtils.getLimit(), PageUtils.getSize());
        // 查询说说评论量
        List<Integer> talkIdList = talkVOList.stream()
                .map(TalkVO::getId)
                .collect(Collectors.toList());
        List<CommentCountVO> commentCountVOList = commentMapper.selectCommentCountByTypeId(talkIdList, TALK.getType());
        Map<Integer, Integer> commentCountMap = commentCountVOList.stream()
                .collect(Collectors.toMap(CommentCountVO::getId, CommentCountVO::getCommentCount));
        // 查询说说点赞量
        Map<String, Integer> likeCountMap = redisService.getHashAll(TALK_LIKE_COUNT);
        talkVOList.forEach(item -> {
            item.setLikeCount(Optional.ofNullable(likeCountMap.get(item.getId().toString())).orElse(0));
            item.setCommentCount(Optional.ofNullable(commentCountMap.get(item.getId())).orElse(0));
            // 转换图片格式
            if (Objects.nonNull(item.getImages())) {
                item.setImgList(CommUtils.castList(JSON.parseObject(item.getImages(), List.class), String.class));
            }
        });
        return new PageResult<>(talkVOList, count);
    }

    @Override
    public TalkVO getTalkById(Integer talkId) {
        // 查询说说信息
        TalkVO talkVO = talkMapper.selectTalkById(talkId);
        if (Objects.isNull(talkVO)) {
            return null;
        }
        // 查询说说点赞量
        Integer likeCount = redisService.getHash(TALK_LIKE_COUNT, talkId.toString());
        talkVO.setLikeCount(Optional.ofNullable(likeCount).orElse(0));
        // 转换图片格式
        if (Objects.nonNull(talkVO.getImages())) {
            talkVO.setImgList(CommUtils.castList(JSON.parseObject(talkVO.getImages(), List.class), String.class));
        }
        return talkVO;
    }
}
