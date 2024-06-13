package com.tanyinghao.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.mapper.TaskMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.StatusDTO;
import com.tanyinghao.model.dto.TaskDTO;
import com.tanyinghao.model.dto.TaskRunDTO;
import com.tanyinghao.model.entity.Task;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.TaskBackVO;
import com.tanyinghao.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TaskServiceImpl
 * @Description 定时任务服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/13 12:10
 * @Version 1.0
 **/
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {
    @Override
    public PageResult<TaskBackVO> listTaskBackVO(ConditionDTO condition) {
        return null;
    }

    @Override
    public void addTask(TaskDTO task) {

    }

    @Override
    public void updateTask(TaskDTO task) {

    }

    @Override
    public void deleteTask(List<Integer> taskIdList) {

    }

    @Override
    public void updateTaskStatus(StatusDTO taskStatus) {

    }

    @Override
    public void runTask(TaskRunDTO taskRun) {

    }
}
