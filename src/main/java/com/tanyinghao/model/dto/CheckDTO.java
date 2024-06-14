package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName CheckDTO
 * @Description 审核DTO
 * @Author 谭颍豪
 * @Date 2024/6/14 14:10
 * @Version 1.0
 **/
@Data
@ApiModel(description = "审核DTO")
public class CheckDTO {
    /**
     * id集合
     */
    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id集合")
    private List<Integer> idList;

    /**
     * 是否通过 (0否 1是)
     */
    @NotNull(message = "状态值不能为空")
    @ApiModelProperty(value = "是否通过 (0否 1是)")
    private Integer isCheck;
}
