package com.tanyinghao.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.tanyinghao.comm.enums.LoginTypeEnum;
import com.tanyinghao.comm.utils.CommUtils;
import com.tanyinghao.comm.utils.SecurityUtils;
import com.tanyinghao.mapper.UserMapper;
import com.tanyinghao.mapper.UserRoleMapper;
import com.tanyinghao.model.dto.CodeDTO;
import com.tanyinghao.model.dto.LoginDTO;
import com.tanyinghao.model.dto.MailDTO;
import com.tanyinghao.model.dto.RegisterDTO;
import com.tanyinghao.model.entity.SiteConfig;
import com.tanyinghao.model.entity.User;
import com.tanyinghao.model.entity.UserRole;
import com.tanyinghao.service.LoginService;
import com.tanyinghao.service.RedisService;
import com.tanyinghao.strategy.context.SocialLoginStrategyContext;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;


import static com.tanyinghao.comm.constant.CommConstant.*;
import static com.tanyinghao.comm.constant.MqConstant.EMAIL_EXCHANGE;
import static com.tanyinghao.comm.constant.MqConstant.EMAIL_SIMPLE_KEY;
import static com.tanyinghao.comm.constant.RedisConstant.*;
import static com.tanyinghao.comm.enums.LoginTypeEnum.EMAIL;
import static com.tanyinghao.comm.enums.RoleEnum.USER;

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

    @Autowired
    private RedisService redisService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private SocialLoginStrategyContext socialLoginStrategyContext;

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

    @Override
    public void sendCode(String username) {
        Assert.isTrue(CommUtils.checkEmail(username), "请输入正确的邮箱！");
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 6);
        String code = randomGenerator.generate();
        MailDTO mailDTO = MailDTO.builder()
                .toEmail(username)
                .subject(CAPTCHA)
                .content("您的验证码为 " + code + " 有效期为" + CODE_EXPIRE_TIME + "分钟")
                .build();
        // 验证码存入消息队列
        rabbitTemplate.convertAndSend(EMAIL_EXCHANGE, EMAIL_SIMPLE_KEY, mailDTO);
        // 验证码存入redis中
        redisService.setObject(CODE_KEY + username, code, CODE_EXPIRE_TIME, TimeUnit.MINUTES);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(RegisterDTO register) {
        // 1. 校验验证码是否存在
        verifyCode(register.getUsername(),register.getCode());
        // 2. 校验用户是否已注册过
        User user = userMapper.selectOne( new LambdaQueryWrapper<User>()
                .select(User::getUsername)
                .eq(User::getUsername, register.getUsername()));
        Assert.isNull(user, "邮箱已经注册");
        // 3.新增用户
        SiteConfig siteConfig = redisService.getObject(SITE_SETTING);
        User newUser = User.builder()
                .username(register.getUsername())
                .email(register.getUsername())
                .nickname(USER_NICKNAME + IdWorker.getId())
                .avatar(siteConfig.getUserAvatar())
                .password(SecurityUtils.sha256Encrypt(register.getPassword()))
                .loginType(EMAIL.getLoginType())
                .isDisable(FALSE)
                .build();
        userMapper.insert(newUser);
        // 绑定用户角色
        UserRole userRole = UserRole.builder()
                .userId(newUser.getId())
                .roleId(USER.getRoleId())
                .build();
        userRoleMapper.insert(userRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String giteeLogin(CodeDTO data) {
        return socialLoginStrategyContext.executeLoginStrategy(data, LoginTypeEnum.GITEE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String githubLogin(CodeDTO data) {
        return socialLoginStrategyContext.executeLoginStrategy(data, LoginTypeEnum.GITHUB);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String qqLogin(CodeDTO data) {
        return socialLoginStrategyContext.executeLoginStrategy(data, LoginTypeEnum.QQ);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 校验验证码
     * @Date 22:59 2024/5/6
     * @Param [username, code]
     **/
    private void verifyCode(String username, String code) {
        String storeCode = redisService.getObject(CODE_KEY + username);
        Assert.notBlank(storeCode, "验证码未发送或者已经过期");
        Assert.isTrue(storeCode.equals(code), "验证码错误，请重新输入！");
    }
}
