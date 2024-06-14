package com.tanyinghao.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.comm.enums.FilePathEnum;
import com.tanyinghao.comm.utils.BeanCopyUtils;
import com.tanyinghao.comm.utils.CommUtils;
import com.tanyinghao.comm.utils.FileUtils;
import com.tanyinghao.comm.utils.PageUtils;
import com.tanyinghao.mapper.BlogFileMapper;
import com.tanyinghao.mapper.TalkMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.TalkDTO;
import com.tanyinghao.model.entity.BlogFile;
import com.tanyinghao.model.entity.Talk;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.TalkBackInfoVO;
import com.tanyinghao.model.vo.TalkBackVO;
import com.tanyinghao.service.TalkService;
import com.tanyinghao.strategy.context.UploadStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.tanyinghao.comm.constant.CommConstant.FALSE;

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
}
