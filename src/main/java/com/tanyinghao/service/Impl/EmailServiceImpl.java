package com.tanyinghao.service.Impl;

import com.tanyinghao.model.dto.MailDTO;
import com.tanyinghao.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @ClassName EmailServiceImpl
 * @Description 邮件服务具体实现
 * @Author 谭颍豪
 * @Date 2024/5/4 11:22
 * @Version 1.0
 **/
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String email;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendSimpleMail(MailDTO mailDTO) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(email);
        simpleMailMessage.setTo(mailDTO.getToEmail());
        simpleMailMessage.setSubject(mailDTO.getSubject());
        simpleMailMessage.setText(mailDTO.getContent());
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendHtmlMail(MailDTO mailDTO) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            Context context = new Context();
            context.setVariables(mailDTO.getContentMap());
            String process = templateEngine.process(mailDTO.getTemplate(), context);
            mimeMessageHelper.setFrom(email);
            mimeMessageHelper.setTo(mailDTO.getToEmail());
            mimeMessageHelper.setSubject(mailDTO.getSubject());
            mimeMessageHelper.setText(process,true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
