package com.tanyinghao.service;

import com.tanyinghao.model.dto.CodeDTO;
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

    /**
     *
     * @Author TanYingHao
     * @Description gitee登录
     * @Date 20:28 2024/5/7
     * @Param [data] 第三方code
     * @return java.lang.String Token
     **/
    String giteeLogin(CodeDTO data);

    /**
     *
     * @Author TanYingHao
     * @Description github登录
     * @Date 20:29 2024/5/7
     * @Param [data] 第三方code
     * @return java.lang.String Token
     **/
    String githubLogin(CodeDTO data);

    /**
     *
     * @Author TanYingHao
     * @Description qq登录
     * @Date 20:30 2024/5/7
     * @Param [data] 第三方code
     * @return java.lang.String Token
     **/
    String qqLogin(CodeDTO data);
}
