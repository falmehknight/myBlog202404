package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @ClassName MailDTO
 * @Description 邮箱信息
 * @Author 谭颍豪
 * @Date 2024/5/3 23:37
 * @Version 1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "邮箱信息DTO")
public class MailDTO {

    @ApiModelProperty(value = "接收者邮箱号")
    private String toEmail;

    @ApiModelProperty(value = "主题")
    private String subject;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "内容信息")
    private Map<String, Object> contentMap;

    @ApiModelProperty(value = "邮箱模板")
    private String template;

}
