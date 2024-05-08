package com.tanyinghao.strategy.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.tanyinghao.mapper.UserMapper;
import com.tanyinghao.mapper.UserRoleMapper;
import com.tanyinghao.model.dto.CodeDTO;
import com.tanyinghao.model.entity.User;
import com.tanyinghao.model.entity.UserRole;
import com.tanyinghao.model.vo.SocialTokenVO;
import com.tanyinghao.model.vo.SocialUserInfoVO;
import com.tanyinghao.strategy.SocialLoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.tanyinghao.comm.enums.RoleEnum.USER;

/**
 * @ClassName AbstractLoginStrategyImpl
 * @Description 抽象登录模板实现类
 * @Author 谭颍豪
 * @Date 2024/5/7 23:08
 * @Version 1.0
 **/
@Service
public abstract class AbstractLoginStrategyImpl implements SocialLoginStrategy {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public String login(CodeDTO data) {
        User user;
        // 获取token
        SocialTokenVO socialToken = getSocialToken(data);
        // 获取用户信息
        SocialUserInfoVO socialUserInfo = getSocialUserInfo(socialToken);
        // 判断是否已经注册过了
        User user1 = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getId)
                .eq(User::getUsername, socialUserInfo.getId())
                .eq(User::getLoginType, socialToken.getLoginType()));
        if (Objects.isNull(user1)) {
            user = saveLoginUser(socialToken,socialUserInfo);
        } else {
            user = user1;
        }
        // 检查指定账号是否被封禁
        StpUtil.isDisable(user.getId());
        // 登录
        StpUtil.login(user.getId());
        return StpUtil.getTokenValue();
    }
    /**
     *
     * @Author TanYingHao
     * @Description 获取第三方的token
     * @Date 23:16 2024/5/7
     * @Param [data]
     * @return com.tanyinghao.model.vo.SocialTokenVO
     **/
    public abstract SocialTokenVO getSocialToken(CodeDTO data);

    /**
     *
     * @Author TanYingHao
     * @Description 获取第三方用户信息
     * @Date 23:17 2024/5/7
     * @Param [socialTokenVO] 第三方的token
     * @return com.tanyinghao.model.vo.SocialUserInfoVO
     **/
    public abstract SocialUserInfoVO getSocialUserInfo(SocialTokenVO socialTokenVO);


    /**
     *
     * @Author TanYingHao
     * @Description 新增用户账号
     * @Date 23:18 2024/5/7
     * @Param [socialTokenVO, socialUserInfoVO]
     * @return com.tanyinghao.model.entity.User 登录用户身份权限
     **/
    private User saveLoginUser(SocialTokenVO socialTokenVO, SocialUserInfoVO socialUserInfoVO) {
        // 1.新增用户信息
        User user = User.builder()
                .avatar(socialUserInfoVO.getAvatar())
                .nickname(socialUserInfoVO.getNickname())
                .username(socialUserInfoVO.getId())
                .password(socialTokenVO.getAccessToken())
                .loginType(socialTokenVO.getLoginType())
                .build();
        userMapper.insert(user);
        // 新增用户角色
        UserRole userRole = UserRole.builder()
                .userId(user.getId())
                .roleId(USER.getRoleId())
                .build();
        userRoleMapper.insert(userRole);
        return user;

    }

}
