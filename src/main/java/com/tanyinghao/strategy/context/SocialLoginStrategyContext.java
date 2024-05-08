package com.tanyinghao.strategy.context;

import com.tanyinghao.comm.enums.LoginTypeEnum;
import com.tanyinghao.model.dto.CodeDTO;
import com.tanyinghao.strategy.SocialLoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName SocialLoginStrategyContext
 * @Description 登录策略上下文
 * @Author 谭颍豪
 * @Date 2024/5/8 20:00
 * @Version 1.0
 **/
@Service
public class SocialLoginStrategyContext {

    // 这种注入会将对应的SocialLoginStrategy实现类注入进来，key为实现类首字母变小写
    @Autowired
    private Map<String, SocialLoginStrategy> socialLoginStrategyMap;
    /**
     *
     * @Author TanYingHao
     * @Description 登录
     * @Date 20:02 2024/5/8
     * @Param [data, loginTypeEnum] 登录code，登录枚举
     * @return java.lang.String
     **/
    public String executeLoginStrategy(CodeDTO data, LoginTypeEnum loginTypeEnum) {
        return socialLoginStrategyMap.get(loginTypeEnum.getStrategy()).login(data);
    }
}
