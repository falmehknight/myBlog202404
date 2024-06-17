package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName WebsocketMessageDTO
 * @Description websocket消息DTO
 * @Author 谭颍豪
 * @Date 2024/6/17 15:31
 * @Version 1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "websocket消息DTO")
public class WebsocketMessageDTO {
    /**
     * 类型
     */
    private Integer type;

    /**
     * 数据
     */
    private Object data;

}
