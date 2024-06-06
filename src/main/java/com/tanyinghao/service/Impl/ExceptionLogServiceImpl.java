package com.tanyinghao.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.mapper.ExceptionLogMapper;
import com.tanyinghao.model.entity.ExceptionLog;
import com.tanyinghao.service.ExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
