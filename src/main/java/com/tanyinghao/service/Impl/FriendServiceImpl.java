package com.tanyinghao.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.comm.utils.BeanCopyUtils;
import com.tanyinghao.comm.utils.PageUtils;
import com.tanyinghao.mapper.FriendMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.FriendDTO;
import com.tanyinghao.model.entity.Friend;
import com.tanyinghao.model.vo.FriendBackVO;
import com.tanyinghao.model.vo.FriendVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName FriendServiceImpl
 * @Description 友链服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/15 18:15
 * @Version 1.0
 **/
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public PageResult<FriendBackVO> listFriendBackVO(ConditionDTO condition) {
        // 查询友链数量
        Long count = friendMapper.selectCount(new LambdaQueryWrapper<Friend>()
                .like(StringUtils.hasText(condition.getKeyword()), Friend::getName, condition.getKeyword())
        );
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询后台友链列表
        List<FriendBackVO> friendBackVOList = friendMapper.selectFriendBackVOList(PageUtils.getLimit(), PageUtils.getSize(), condition.getKeyword());
        return new PageResult<>(friendBackVOList, count);
    }

    @Override
    public void addFriend(FriendDTO friend) {
        // 新友链
        Friend newFriend = BeanCopyUtils.copyBean(friend, Friend.class);
        // 添加友链
        baseMapper.insert(newFriend);
    }

    @Override
    public void updateFriend(FriendDTO friend) {
        // 新友链
        Friend newFriend = BeanCopyUtils.copyBean(friend, Friend.class);
        // 更新友链
        baseMapper.updateById(newFriend);
    }

    @Override
    public List<FriendVO> listFriendVO() {
        // 查询友链列表
        return friendMapper.selectFriendVOList();
    }
}
