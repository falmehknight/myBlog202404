package com.tanyinghao.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.tanyinghao.comm.constant.MqConstant.*;

/**
 * @ClassName RabbitMqConfig
 * @Description RabbitMQ配置
 * @Author 谭颍豪
 * @Date 2024/5/4 16:28
 * @Version 1.0
 **/
@Configuration
public class RabbitMqConfig {

    /**
     *
     * @Author TanYingHao
     * @Description 消息转换器
     * @Date 16:34 2024/5/4
     **/
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 邮件交换机
     * @Date 16:35 2024/5/4
     **/
    @Bean
    public TopicExchange emailExchange() {
        return new TopicExchange(EMAIL_EXCHANGE, true, false);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 邮件Simple队列
     * @Date 16:37 2024/5/4
     **/
    @Bean
    public Queue emailSimpleQueue() {
        return new Queue(EMAIL_SIMPLE_QUEUE,true);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 邮件HTML队列
     * @Date 16:37 2024/5/4
     **/
    @Bean
    public Queue emailHtmlQueue() {
        return new Queue(EMAIL_HTML_QUEUE, true);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 绑定邮件Simple队列
     * @Date 16:40 2024/5/4
     **/
    @Bean
    public Binding simpleQueueBinding() {
        return BindingBuilder.bind(emailSimpleQueue()).to(emailExchange()).with(EMAIL_SIMPLE_KEY);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 绑定邮件Html队列
     * @Date 16:40 2024/5/4
     **/
    @Bean
    public Binding htmlQueueBinding() {
        return BindingBuilder.bind(emailHtmlQueue()).to(emailExchange()).with(EMAIL_HTML_KEY);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 文章交换机
     * @Date 16:42 2024/5/4
     **/
    @Bean
    public TopicExchange articleExchange() {
        return new TopicExchange(ARTICLE_EXCHANGE, true, false);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 文章队列
     * @Date 16:43 2024/5/4
     **/
    @Bean
    public Queue articleQueue() {
        return new Queue(ARTICLE_QUEUE, true);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 绑定文章队列
     * @Date 16:43 2024/5/4
     **/
    @Bean
    public Binding articleQueueBinding() {
        return BindingBuilder.bind(articleQueue()).to(articleExchange()).with(ARTICLE_KEY);
    }
}
