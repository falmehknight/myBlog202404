package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName DeleteDTO
 * @Description 逻辑删除DTO
 * @Author 谭颍豪
 * @Date 2024/5/10 22:28
 * @Version 1.0
 **/
@Data
@ApiModel(description = "逻辑删除DTO")
public class DeleteDTO {
    /**
     * id列表
     */
    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id列表")
    private List<Integer> idList;

    /**
     * 是否删除 (0否 1是)
     */
    @NotNull(message = "状态值不能为空")
    @ApiModelProperty(value = "是否删除 (0否 1是)")
    private Integer isDelete;
}
