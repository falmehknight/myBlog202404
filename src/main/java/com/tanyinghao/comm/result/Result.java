package com.tanyinghao.comm.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import static com.tanyinghao.comm.enums.StatusCodeEnum.FAIL;
import static com.tanyinghao.comm.enums.StatusCodeEnum.SUCCESS;

/**
 * @ClassName Result
 * @Description 结果返回类
 * @Author 谭颍豪
 * @Date 2024/4/29 23:09
 * @Version 1.0
 **/
@ApiModel(description = "结果返回类")
@Data
public class Result<T> {

    @ApiModelProperty("返回状态")
    private Boolean flag;

    @ApiModelProperty("状态码")
    private Integer code;

    @ApiModelProperty("返回信息")
    private String msg;

    @ApiModelProperty("返回数据")
    private T data;

    public static <T> Result<T> success() {
        return buildResult(true,null,SUCCESS.getCode(), SUCCESS.getMsg());
    }

    public static <T> Result<T> success(T data) {
        return buildResult(true, data, SUCCESS.getCode(), SUCCESS.getMsg());
    }

    public static <T> Result<T> fail(String msg) {
        return buildResult(false, null, FAIL.getCode(), msg);
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        return buildResult(false, null, code, msg);
    }

    private static <T> Result<T> buildResult(Boolean flag, T data, Integer code, String message) {
        Result<T> r = new Result<>();
        r.setFlag(flag);
        r.setData(data);
        r.setCode(code);
        r.setMsg(message);
        return r;
    }
}
