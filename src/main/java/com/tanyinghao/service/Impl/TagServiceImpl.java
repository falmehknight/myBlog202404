package com.tanyinghao.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.mapper.TagMapper;
import com.tanyinghao.model.entity.Tag;
import com.tanyinghao.service.TagService;
import org.springframework.stereotype.Service;

/**
 * @ClassName TagServiceImpl
 * @Description 标签服务接口
 * @Author 谭颍豪
 * @Date 2024/6/12 14:53
 * @Version 1.0
 **/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
