package com.tanyinghao.consumer;

import cn.hutool.json.JSONUtil;
import com.tanyinghao.model.dto.CanalDTO;
import com.tanyinghao.model.vo.ArticleSearchVO;
import com.tanyinghao.service.ElasticsearchService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import static com.tanyinghao.comm.constant.ElasticConstant.*;
import static com.tanyinghao.comm.constant.MqConstant.*;

/**
 * @ClassName ArticleConsumer
 * @Description 文章消费者
 * @Author 谭颍豪
 * @Date 2024/6/17 11:03
 * @Version 1.0
 **/
@Component
public class ArticleConsumer {
    @Autowired
    private ElasticsearchService elasticsearchService;

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = ARTICLE_QUEUE, durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = ARTICLE_EXCHANGE, type = ExchangeTypes.TOPIC),
                    key = ARTICLE_KEY
            )})
    public void listenSaveArticle(Message message) {
        String data = new String(message.getBody(), StandardCharsets.UTF_8);
        CanalDTO canalDTO = JSONUtil.toBean(JSONUtil.toJsonStr(data), CanalDTO.class);
        if (canalDTO.getIsDdl()) {
            return;
        }
        ArticleSearchVO article = JSONUtil.toBean(JSONUtil.toJsonStr(canalDTO.getData().get(0)), ArticleSearchVO.class);
        switch (canalDTO.getType()) {
            case INSERT:
                elasticsearchService.addArticle(article);
            case UPDATE:
                elasticsearchService.updateArticle(article);
                break;
            case DELETE:
                elasticsearchService.deleteArticle(article.getId());
                break;
            default:
                break;
        }
    }
}
