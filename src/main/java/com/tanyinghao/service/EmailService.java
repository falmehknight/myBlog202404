package com.tanyinghao.service;

import com.tanyinghao.model.dto.MailDTO;

/**
 *
 * @Author TanYingHao
 * @Description 邮件服务接口
 * @Date 11:20 2024/5/4
 **/
public interface EmailService {

    /**
     *
     * @Author TanYingHao
     * @Description 发送简单邮件
     * @Date 11:21 2024/5/4
     * @Param [mailDTO] 邮件信息
     **/
    void sendSimpleMail(MailDTO mailDTO);

    /**
     *
     * @Author TanYingHao
     * @Description 发送HTML邮件
     * @Date 11:21 2024/5/4
     * @Param [mailDTO] 邮件信息
     **/
    void sendHtmlMail(MailDTO mailDTO);
}
