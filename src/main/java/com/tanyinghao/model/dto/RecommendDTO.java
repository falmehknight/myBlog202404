package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName RecommendDTO
 * @Description 推荐DTO
 * @Author 谭颍豪
 * @Date 2024/5/10 22:46
 * @Version 1.0
 **/
@Data
@ApiModel(description = "推荐DTO")
public class RecommendDTO {
    /**
     * id
     */
    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 是否推荐 (0否 1是)
     */
    @NotNull(message = "推荐状态不能为空")
    @ApiModelProperty(value = "是否推荐 (0否 1是)")
    private Integer isRecommend;
}
