package com.tanyinghao.service;

import com.tanyinghao.model.dto.LoginDTO;

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
}
