package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.CheckDTO;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.MessageDTO;
import com.tanyinghao.model.entity.Message;
import com.tanyinghao.model.vo.MessageBackVO;
import com.tanyinghao.model.vo.MessageVO;
import com.tanyinghao.model.vo.PageResult;

import java.util.List;

public interface MessageService extends IService<Message> {

    /**
     *
     * @Author TanYingHao
     * @Description 查看后台留言列表
     * @Date 15:54 2024/6/15
     * @Param [condition]
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.MessageBackVO>
     **/
    PageResult<MessageBackVO> listMessageBackVO(ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 审核留言
     * @Date 15:55 2024/6/15
     * @Param [check]
     **/
    void updateMessageCheck(CheckDTO check);

    /**
     *
     * @Author TanYingHao
     * @Description 查看留言列表
     * @Date 15:57 2024/6/15
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.MessageVO>
     **/
    List<MessageVO> listMessageVO();

    /**
     *
     * @Author TanYingHao
     * @Description 添加留言
     * @Date 15:58 2024/6/15
     * @Param [message]
     **/
    void addMessage(MessageDTO message);
}
