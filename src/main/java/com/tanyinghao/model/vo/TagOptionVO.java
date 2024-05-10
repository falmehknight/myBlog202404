package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName TagOptionVO
 * @Description 文章标签VO
 * @Author 谭颍豪
 * @Date 2024/5/10 22:06
 * @Version 1.0
 **/
@Data
@ApiModel(description = "文章标签VO")
public class TagOptionVO {
    /**
     * 标签id
     */
    @ApiModelProperty(value = "标签id")
    private Integer id;

    /**
     * 标签名
     */
    @ApiModelProperty(value = "标签名")
    private String tagName;
}
