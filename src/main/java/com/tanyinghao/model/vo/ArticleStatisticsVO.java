package com.tanyinghao.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ArticleStatisticsVO
 * @Description 文章贡献统计
 * @Author 谭颍豪
 * @Date 2024/6/15 18:43
 * @Version 1.0
 **/
@Data
@ApiModel(description = "文章贡献统计")
public class ArticleStatisticsVO {
    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private String date;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer count;
}
