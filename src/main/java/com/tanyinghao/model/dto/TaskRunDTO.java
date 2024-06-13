package com.tanyinghao.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName TaskRunDTO
 * @Description 定时任务运行
 * @Author 谭颍豪
 * @Date 2024/6/13 12:26
 * @Version 1.0
 **/
@Data
@ApiModel(description = "定时任务运行")
public class TaskRunDTO {
    /**
     * 任务id
     */
    @ApiModelProperty(value = "任务id")
    private Integer id;

    /**
     * 任务组别
     */
    @ApiModelProperty(value = "任务组别")
    private String taskGroup;
}
