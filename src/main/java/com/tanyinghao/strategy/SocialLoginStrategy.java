package com.tanyinghao.strategy;

import com.tanyinghao.model.dto.CodeDTO;

public interface SocialLoginStrategy {

    /**
     *
     * @Author TanYingHao
     * @Description 登录
     * @Date 20:33 2024/5/7
     * @Param [data] 第三方code
     * @return java.lang.String token
     **/
    String login(CodeDTO data);
}
