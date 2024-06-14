package com.tanyinghao.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.mapper.TalkMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.TalkDTO;
import com.tanyinghao.model.entity.Talk;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.TalkBackInfoVO;
import com.tanyinghao.model.vo.TalkBackVO;
import com.tanyinghao.service.TagService;
import com.tanyinghao.service.TalkService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName TalkServiceImpl
 * @Description 说说服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/14 9:54
 * @Version 1.0
 **/
@Service
public class TalkServiceImpl extends ServiceImpl<TalkMapper, Talk> implements TalkService {
    @Override
    public PageResult<TalkBackVO> listTalkBackVO(ConditionDTO condition) {
        return null;
    }

    @Override
    public String uploadTalkCover(MultipartFile file) {
        return null;
    }

    @Override
    public void addTalk(TalkDTO talk) {

    }

    @Override
    public void deleteTalk(Integer talkId) {

    }

    @Override
    public void updateTalk(TalkDTO talk) {

    }

    @Override
    public TalkBackInfoVO editTalk(Integer talkId) {
        return null;
    }
}
