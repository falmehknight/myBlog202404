package com.tanyinghao.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.mapper.MessageMapper;
import com.tanyinghao.model.dto.CheckDTO;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.MessageDTO;
import com.tanyinghao.model.entity.Message;
import com.tanyinghao.model.vo.MessageBackVO;
import com.tanyinghao.model.vo.MessageVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MessageServiceImpl
 * @Description 留言服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/15 15:49
 * @Version 1.0
 **/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    @Override
    public PageResult<MessageBackVO> listMessageBackVO(ConditionDTO condition) {
        return null;
    }

    @Override
    public void updateMessageCheck(CheckDTO check) {

    }

    @Override
    public List<MessageVO> listMessageVO() {
        return null;
    }

    @Override
    public void addMessage(MessageDTO message) {

    }
}
