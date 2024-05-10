package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName TopDTO
 * @Description 置顶文章
 * @Author 谭颍豪
 * @Date 2024/5/10 22:42
 * @Version 1.0
 **/
@Data
@ApiModel(description = "置顶DTO")
public class TopDTO {
    /**
     * id
     */
    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 是否置顶 (0否 1是)
     */
    @NotNull(message = "置顶状态不能为空")
    @ApiModelProperty(value = "是否置顶 (0否 1是)")
    private Integer isTop;
}
