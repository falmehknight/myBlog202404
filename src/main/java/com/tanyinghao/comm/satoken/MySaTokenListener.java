package com.tanyinghao.comm.satoken;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tanyinghao.comm.utils.IpUtils;
import com.tanyinghao.comm.utils.UserAgentUtils;
import com.tanyinghao.mapper.UserMapper;
import com.tanyinghao.model.entity.User;
import com.tanyinghao.model.vo.OnlineVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

import static com.tanyinghao.comm.constant.CommConstant.ONLINE_USER;
import static com.tanyinghao.comm.enums.ZoneEnum.SHANGHAI;

/**
 * @ClassName MySaTokenListener
 * @Description 自定义侦听器实现
 * @Author 谭颍豪
 * @Date 2024/6/7 17:35
 * @Version 1.0
 **/
@Component
public class MySaTokenListener implements SaTokenListener {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {
        // 查询用户昵称
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getAvatar, User::getNickname)
                .eq(User::getId, loginId));
        // 解析browser和os
        Map<String, String> userAgentMap = UserAgentUtils.parseOsAndBrowser(request.getHeader("User-Agent"));
        // 获取登录ip地址
        String ipAddress = IpUtils.getIpAddress(request);
        // 获取登录地址
        String ipSource = IpUtils.getIpSource(ipAddress);
        // 获取登录时间
        LocalDateTime loginTime = LocalDateTime.now(ZoneId.of(SHANGHAI.getZone()));
        OnlineVO onlineVO = OnlineVO.builder()
                .id((Integer) loginId)
                .token(tokenValue)
                .avatar(user.getAvatar())
                .nickname(user.getNickname())
                .ipAddress(ipAddress)
                .ipSource(ipSource)
                .os(userAgentMap.get("os"))
                .browser(userAgentMap.get("browser"))
                .loginTime(loginTime)
                .build();
        // 更新用户登录信息
        User newUser = User.builder()
                .id((Integer) loginId)
                .ipAddress(ipAddress)
                .ipSource(ipSource)
                .loginTime(loginTime)
                .build();
        userMapper.updateById(newUser);
        // 用户在线信息存入到tokenSession中
        SaSession tokenSessionByToken = StpUtil.getTokenSessionByToken(tokenValue);
        tokenSessionByToken.set(ONLINE_USER, onlineVO);
    }

    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
            // 删除缓存的用户信息
            StpUtil.logoutByTokenValue(tokenValue);
    }

    @Override
    public void doKickout(String s, Object o, String s1) {

    }

    @Override
    public void doReplaced(String s, Object o, String s1) {

    }

    @Override
    public void doDisable(String s, Object o, String s1, int i, long l) {

    }

    @Override
    public void doUntieDisable(String s, Object o, String s1) {

    }

    @Override
    public void doOpenSafe(String s, String s1, String s2, long l) {

    }

    @Override
    public void doCloseSafe(String s, String s1, String s2) {

    }

    @Override
    public void doCreateSession(String s) {

    }

    @Override
    public void doLogoutSession(String s) {

    }

    @Override
    public void doRenewTimeout(String s, Object o, long l) {

    }
}
