package com.tanyinghao.service;

import com.tanyinghao.model.dto.LoginDTO;
import com.tanyinghao.model.dto.RegisterDTO;

public interface LoginService {

    /**
     *
     * @Author TanYingHao
     * @Description 用户登录
     * @Date 18:27 2024/5/1
     * @Param [loginDTO]
     * @return java.lang.String
     **/
    String login(LoginDTO loginDTO);

    /**
     *
     * @Author TanYingHao
     * @Description 发送邮箱验证码
     * @Date 23:27 2024/5/3
     * @Param [username]
     **/
    void sendCode(String username);

    /**
     *
     * @Author TanYingHao
     * @Description 邮箱注册
     * @Date 22:51 2024/5/6
     * @Param [register]
     * @return void
     **/
    void register(RegisterDTO register);
}
