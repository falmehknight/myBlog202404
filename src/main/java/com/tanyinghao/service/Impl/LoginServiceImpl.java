package com.tanyinghao.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tanyinghao.mapper.UserMapper;
import com.tanyinghao.model.dto.LoginDTO;
import com.tanyinghao.model.entity.User;
import com.tanyinghao.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @ClassName LoginService
 * @Description 登录模块实现类
 * @Author 谭颍豪
 * @Date 2024/5/1 18:28
 * @Version 1.0
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(LoginDTO loginDTO) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                        .select(User::getId)
                        .eq(User::getUsername, loginDTO.getUserName())
                        .eq(User::getPassword, loginDTO.getPassWord())
        );
        Assert.notNull(user,"用户不存在或者密码错误");
        // 检查账号是否被封禁 sa-token框架
        StpUtil.checkDisable(user.getId());
        // 登录
        StpUtil.login(user.getId());
        return StpUtil.getTokenValue();
    }
}
