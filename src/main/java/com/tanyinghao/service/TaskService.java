package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.StatusDTO;
import com.tanyinghao.model.dto.TaskDTO;
import com.tanyinghao.model.dto.TaskRunDTO;
import com.tanyinghao.model.entity.Task;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.TaskBackVO;

import java.util.List;

public interface TaskService extends IService<Task> {

    /**
     *
     * @Author TanYingHao
     * @Description 查看定时任务列表
     * @Date 12:14 2024/6/13
     * @Param [condition]
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.TaskBackVO>
     **/
    PageResult<TaskBackVO> listTaskBackVO(ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 添加定时任务
     * @Date 12:15 2024/6/13
     * @Param [task]
     **/
    void addTask(TaskDTO task);

    /**
     *
     * @Author TanYingHao
     * @Description 修改定时任务
     * @Date 12:18 2024/6/13
     * @Param [task]
     **/
    void updateTask(TaskDTO task);

    /**
     *
     * @Author TanYingHao
     * @Description 删除定时任务
     * @Date 12:19 2024/6/13
     * @Param [taskIdList]
     **/
    void deleteTask(List<Integer> taskIdList);

    /**
     *
     * @Author TanYingHao
     * @Description 更新定时任务状态
     * @Date 12:26 2024/6/13
     * @Param [taskStatus]
     **/
    void updateTaskStatus(StatusDTO taskStatus);

    /**
     *
     * @Author TanYingHao
     * @Description 执行定时任务
     * @Date 12:27 2024/6/13
     * @Param [taskRun]
     **/
    void runTask(TaskRunDTO taskRun);
}
