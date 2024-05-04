package com.tanyinghao.consumer;

import com.tanyinghao.model.dto.MailDTO;
import com.tanyinghao.service.EmailService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.tanyinghao.comm.constant.MqConstant.*;

/**
 * @ClassName EmailConsumer
 * @Description 邮件消费者
 * @Author 谭颍豪
 * @Date 2024/5/4 15:49
 * @Version 1.0
 **/
@Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService;

    @RabbitListener(bindings = {
            @QueueBinding(
                value = @Queue(value = EMAIL_SIMPLE_QUEUE, durable = "true", autoDelete = "false"),
                exchange = @Exchange(value = EMAIL_EXCHANGE, type = ExchangeTypes.TOPIC),
                key = EMAIL_SIMPLE_KEY
            )})
    public void listenSendSimpleEmail(@Payload MailDTO mailDTO) {
        emailService.sendSimpleMail(mailDTO);
    }

    @RabbitListener( bindings = {
            @QueueBinding(
                    value = @Queue(value = EMAIL_HTML_QUEUE, durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = EMAIL_EXCHANGE, type = ExchangeTypes.TOPIC),
                    key = EMAIL_HTML_KEY
            )})
    public void listenSendHtmlEmail(@Payload MailDTO mailDTO) {
        emailService.sendHtmlMail(mailDTO);
    }
}
