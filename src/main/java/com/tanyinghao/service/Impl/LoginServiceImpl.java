package com.tanyinghao.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tanyinghao.comm.utils.CommUtils;
import com.tanyinghao.mapper.UserMapper;
import com.tanyinghao.model.dto.LoginDTO;
import com.tanyinghao.model.dto.MailDTO;
import com.tanyinghao.model.entity.User;
import com.tanyinghao.service.LoginService;
import com.tanyinghao.service.RedisService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.tanyinghao.comm.constant.CommConstant.CAPTCHA;
import static com.tanyinghao.comm.constant.MqConstant.EMAIL_EXCHANGE;
import static com.tanyinghao.comm.constant.MqConstant.EMAIL_SIMPLE_KEY;
import static com.tanyinghao.comm.constant.RedisConstant.CODE_EXPIRE_TIME;
import static com.tanyinghao.comm.constant.RedisConstant.CODE_KEY;

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
}
