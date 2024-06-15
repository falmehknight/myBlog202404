package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName PhotoDTO
 * @Description 照片DTO
 * @Author 谭颍豪
 * @Date 2024/6/15 13:53
 * @Version 1.0
 **/
@Data
@ApiModel(description = "照片DTO")
public class PhotoDTO {
    /**
     * 相册id
     */
    @NotNull(message = "相册id不能为空")
    @ApiModelProperty(value = "相册id")
    private Integer albumId;

    /**
     * 照片链接
     */
    @ApiModelProperty(value = "照片链接")
    private List<String> photoUrlList;

    /**
     * 照片id
     */
    @ApiModelProperty(value = "照片id")
    private List<Integer> photoIdList;
}
