package com.tanyinghao.model.dto;

import com.tanyinghao.model.entity.ChatRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ChatRecordDTO
 * @Description 聊天记录DTO
 * @Author 谭颍豪
 * @Date 2024/6/17 15:30
 * @Version 1.0
 **/
@Data
@Builder
@ApiModel(description = "聊天记录DTO")
public class ChatRecordDTO {
    /**
     * 聊天记录
     */
    @ApiModelProperty(value = "聊天记录")
    private List<ChatRecord> chatRecordList;

    /**
     * ip地址
     */
    @ApiModelProperty(value = "ip地址")
    private String ipAddress;

    /**
     * ip来源
     */
    @ApiModelProperty(value = "ip来源")
    private String ipSource;
}
