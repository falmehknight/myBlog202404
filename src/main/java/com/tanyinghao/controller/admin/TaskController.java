package com.tanyinghao.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import com.tanyinghao.comm.annotation.OptLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.StatusDTO;
import com.tanyinghao.model.dto.TaskDTO;
import com.tanyinghao.model.dto.TaskRunDTO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.TaskBackVO;
import com.tanyinghao.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tanyinghao.comm.constant.OptTypeConstant.*;

/**
 * @ClassName TaskController
 * @Description 定时任务控制器
 * @Author 谭颍豪
 * @Date 2024/6/13 12:05
 * @Version 1.0
 **/
@Api(tags = "定时任务模块")
@RestController
@RequestMapping("/admin/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /**
     *
     * @Author TanYingHao
     * @Description 查看定时任务列表
     * @Date 12:11 2024/6/13
     * @Param [condition]
     * @return com.tanyinghao.comm.result.Result<PageResult<TaskBackVO>>
     **/
    @ApiOperation("查看定时任务列表")
    @SaCheckPermission("monitor:task:list")
    @GetMapping("/list")
    public Result<PageResult<TaskBackVO>> listTaskBackVO(ConditionDTO condition) {
        return Result.success(taskService.listTaskBackVO(condition));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 添加定时任务信息
     * @Date 12:14 2024/6/13
     * @Param [task] 任务信息
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = ADD)
    @ApiOperation("添加定时任务")
    @SaCheckPermission("monitor:task:add")
    @PostMapping("/add")
    public Result<?> addTask(@Validated @RequestBody TaskDTO task) {
        taskService.addTask(task);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 修改定时任务
     * @Date 12:16 2024/6/13
     * @Param [task]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = UPDATE)
    @ApiOperation("修改定时任务")
    @SaCheckPermission(value = "monitor:task:update")
    @PutMapping("/update")
    public Result<?> updateTask(@Validated @RequestBody TaskDTO task) {
        taskService.updateTask(task);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 删除定时任务
     * @Date 12:18 2024/6/13
     * @Param [taskIdList]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = DELETE)
    @ApiOperation("删除定时任务")
    @SaCheckPermission("monitor:task:delete")
    @DeleteMapping("/delete")
    public Result<?> deleteTask(@RequestBody List<Integer> taskIdList) {
        taskService.deleteTask(taskIdList);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 修改定时任务状态
     * @Date 12:24 2024/6/13
     * @Param [taskStatus]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = UPDATE)
    @ApiOperation("修改定时任务状态")
    @SaCheckPermission(value = {"monitor:task:update", "monitor:task:status"}, mode = SaMode.OR)
    @PutMapping("/changeStatus")
    public Result<?> updateTaskStatus(@Validated @RequestBody StatusDTO taskStatus) {
        taskService.updateTaskStatus(taskStatus);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 执行定时任务
     * @Date 12:26 2024/6/13
     * @Param [taskRun]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = UPDATE)
    @ApiOperation("执行定时任务")
    @SaCheckPermission("monitor:task:run")
    @PutMapping("/run")
    public Result<?> runTask(@RequestBody TaskRunDTO taskRun) {
        taskService.runTask(taskRun);
        return Result.success();
    }
}
