package com.tanyinghao.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.comm.utils.PageUtils;
import com.tanyinghao.mapper.OperationLogMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.OperationLog;
import com.tanyinghao.model.vo.OperationLogVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Provider;
import java.util.List;

/**
 * @ClassName OperationLogServiceImpl
 * @Description 操作日志业务接口实现类
 * @Author 谭颍豪
 * @Date 2024/6/6 17:39
 * @Version 1.0
 **/
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {
    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public PageResult<OperationLogVO> listOperationLogVO(ConditionDTO condition) {
        Long count = operationLogMapper.selectCount(new LambdaQueryWrapper<OperationLog>()
                .like(StringUtils.hasText(condition.getOptModule()), OperationLog::getModule, condition.getOptModule())
                .or()
                .like(StringUtils.hasText(condition.getKeyword()), OperationLog::getDescription, condition.getKeyword())
        );
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询操作日志列表
        List<OperationLogVO> operationLogVOList = operationLogMapper.selectOperationLogVOList(PageUtils.getLimit(),
                PageUtils.getSize(), condition);
        return new PageResult<>(operationLogVOList, count);
    }

    @Override
    public void saveOperationLog(OperationLog operationLog) {
        // 保存操作日志
        operationLogMapper.insert(operationLog);
    }
}
