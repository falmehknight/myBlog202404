package com.tanyinghao.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.comm.utils.PageUtils;
import com.tanyinghao.mapper.VisitLogMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.VisitLog;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.service.VisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName VisitLogServiceImpl
 * @Description 访问日志业务接口
 * @Author 谭颍豪
 * @Date 2024/5/16 19:44
 * @Version 1.0
 **/
@Service
public class VisitLogServiceImpl extends ServiceImpl<VisitLogMapper, VisitLog> implements VisitLogService {

    @Autowired
    private VisitLogMapper visitLogMapper;
    @Override
    // 保存访问日志
    public void saveVisitLog(VisitLog visitLog) {
        visitLogMapper.insert(visitLog);
    }

    @Override
    public PageResult<VisitLog> listVisitLog(ConditionDTO condition) {
        // 查询访问日志数量
        Long count = visitLogMapper.selectCount(new LambdaQueryWrapper<VisitLog>()
                .like(StringUtils.hasText(condition.getKeyword()), VisitLog::getPage, condition.getKeyword()));
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询访问日志列表
        List<VisitLog> visitLogs = visitLogMapper.selectVisitLogList(PageUtils.getLimit(), PageUtils.getSize(), condition.getKeyword());
        return new PageResult<>(visitLogs, count);
    }
}
