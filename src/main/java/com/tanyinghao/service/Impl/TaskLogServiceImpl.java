package com.tanyinghao.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.comm.utils.PageUtils;
import com.tanyinghao.mapper.TaskLogMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.TaskLog;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.TaskLogVO;
import com.tanyinghao.service.TaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TaskLogServiceImpl
 * @Description 定时任务服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/7 23:16
 * @Version 1.0
 **/
@Service
public class TaskLogServiceImpl extends ServiceImpl<TaskLogMapper, TaskLog> implements TaskLogService {

    @Autowired
    private TaskLogMapper taskLogMapper;

    @Override
    public PageResult<TaskLogVO> listTaskLog(ConditionDTO condition) {
        // 查询定时任务日志数量
        Long count = taskLogMapper.selectTaskLogCount(condition);
        if (count == 0) {
            return new PageResult<>();
        }
        List<TaskLogVO> taskLogVOList = taskLogMapper.selectTaskLogVOList(PageUtils.getLimit(), PageUtils.getSize(), condition);
        return new PageResult<>(taskLogVOList, count);
    }

    @Override
    public void clearTaskLog() {
        taskLogMapper.delete(null);
    }
}
