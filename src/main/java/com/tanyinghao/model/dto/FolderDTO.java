package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName FolderDTO
 * @Description 目录DTO
 * @Author 谭颍豪
 * @Date 2024/6/12 20:50
 * @Version 1.0
 **/
@Data
@ApiModel(description = "目录DTO")
public class FolderDTO {
    /**
     * 文件路径
     */
    @NotBlank(message = "文件路径不能为空")
    @ApiModelProperty(value = "文件路径")
    private String filePath;

    /**
     * 文件名称
     */
    @NotBlank(message = "文件名称不能为空")
    @ApiModelProperty(value = "文件名称")
    private String fileName;
}
