package com.tanyinghao.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.ExceptionLog;
import com.tanyinghao.model.entity.VisitLog;
import com.tanyinghao.model.vo.OperationLogVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.TaskLogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName LogController
 * @Description 日志模块
 * @Author 谭颍豪
 * @Date 2024/6/6 22:41
 * @Version 1.0
 **/
@RestController
@Api(tags = "日志管理模块")
@RequestMapping("/admin")
public class LogController {

    /**
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.PageResult < com.tanyinghao.model.vo.OperationLogVO>>
     * @Author TanYingHao
     * @Description 查看操作日志
     * @Date 22:44 2024/6/6
     * @Param [condition] 条件
     **/
    @ApiOperation(value = "查看操作日志")
    @GetMapping("/operation/list")
    public Result<PageResult<OperationLogVO>> listOperationLogVO(ConditionDTO condition) {
        return Result.success();
    }

    /**
     * @return com.tanyinghao.comm.result.Result<?>
     * @Author TanYingHao
     * @Description 删除操作日志
     * @Date 22:57 2024/6/6
     * @Param [logIdList]
     **/
    @ApiOperation(value = "删除操作日志")
    @DeleteMapping("/operation/delete")
    public Result<?> deleteOperationLog(@RequestBody List<Integer> logIdList) {
        return Result.success();
    }

    /**
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.PageResult < com.tanyinghao.model.entity.ExceptionLog>>
     * @Author TanYingHao
     * @Description 查看异常日志
     * @Date 22:59 2024/6/6
     * @Param [condition] 条件
     **/
    @ApiOperation(value = "查看异常日志")
    @GetMapping("/exception/list")
    public Result<PageResult<ExceptionLog>> listExceptionLog(ConditionDTO condition) {
        return Result.success();
    }

    /**
     * @return com.tanyinghao.comm.result.Result<?>
     * @Author TanYingHao
     * @Description 删除异常日志
     * @Date 23:01 2024/6/6
     * @Param [logIdList] 日志id集合
     **/
    @ApiOperation(value = "删除异常日志")
    @DeleteMapping("/exception/delete")
    public Result<?> deleteExceptionLog(@RequestBody List<Integer> logIdList) {
        return Result.success();
    }

    /**
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.PageResult < com.tanyinghao.model.entity.VisitLog>>
     * @Author TanYingHao
     * @Description 查看访问日志
     * @Date 23:03 2024/6/6
     * @Param [condition]
     **/
    @ApiOperation(value = "查看访问日志")
    @GetMapping("/visit/list")
    public Result<PageResult<VisitLog>> listVisitLog(ConditionDTO condition) {
        return Result.success();
    }

    /**
     * @return com.tanyinghao.comm.result.Result<?>
     * @Author TanYingHao
     * @Description 删除访问日志
     * @Date 23:05 2024/6/6
     * @Param [logIdList]
     **/
    @ApiOperation(value = "删除访问日志")
    @DeleteMapping("/visit/delete")
    public Result<?> deleteVisitLog(@RequestBody List<Integer> logIdList) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看定时任务日志
     * @Date 23:09 2024/6/6
     * @Param [condition]
     * @return com.tanyinghao.comm.result.Result<com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.TaskLogVO>>
     **/
    @ApiOperation("查看定时任务日志")
    @GetMapping("/taskLog/list")
    public Result<PageResult<TaskLogVO>> listTaskLog(ConditionDTO condition) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 删除定时任务日志
     * @Date 23:10 2024/6/6
     * @Param [logIdList]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ApiOperation("删除定时任务日志")
    @DeleteMapping("/taskLog/delete")
    public Result<?> deleteTaskLog(@RequestBody List<Integer> logIdList) {
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 清空定时任务日志
     * @Date 23:11 2024/6/6
     * @Param []
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @ApiOperation("清空定时任务日志")
    @DeleteMapping("taskLog/clear")
    public Result<?> clearTaskLog() {
        return Result.success();
    }
}
