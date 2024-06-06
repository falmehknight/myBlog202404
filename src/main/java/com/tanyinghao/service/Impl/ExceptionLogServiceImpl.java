package com.tanyinghao.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.comm.utils.PageUtils;
import com.tanyinghao.mapper.ExceptionLogMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.ExceptionLog;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.service.ExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName ExceptionLogServiceImpl
 * @Description 异常日志服务接口实现类
 * @Author 谭颍豪
 * @Date 2024/6/6 16:24
 * @Version 1.0
 **/
@Service
public class ExceptionLogServiceImpl extends ServiceImpl<ExceptionLogMapper, ExceptionLog> implements ExceptionLogService {
    @Autowired
    private ExceptionLogMapper exceptionLogMapper;

    @Override
    public void saveExceptionLog(ExceptionLog exceptionLog) {
        // 保存异常日志
        exceptionLogMapper.insert(exceptionLog);
    }

    @Override
    public PageResult<ExceptionLog> listExceptionLog(ConditionDTO conditionDTO) {
        Long count = exceptionLogMapper.selectCount(new LambdaQueryWrapper<ExceptionLog>()
                .like(StringUtils.hasText(conditionDTO.getOptModule()), ExceptionLog::getModule, conditionDTO.getOptModule())
                .or()
                .like(StringUtils.hasText(conditionDTO.getKeyword()), ExceptionLog::getDescription, conditionDTO.getKeyword())
        );
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询异常日志列表
        List<ExceptionLog> exceptionLogs = exceptionLogMapper.selectExceptionLogList(PageUtils.getLimit(), PageUtils.getSize(), conditionDTO);
        return new PageResult<>(exceptionLogs, count);
    }
}
