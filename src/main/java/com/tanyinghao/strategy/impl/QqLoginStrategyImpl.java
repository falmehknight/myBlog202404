package com.tanyinghao.strategy.impl;

import com.alibaba.fastjson2.JSON;
import com.tanyinghao.comm.enums.LoginTypeEnum;
import com.tanyinghao.comm.exception.ServiceException;
import com.tanyinghao.configuration.properties.QqProperties;
import com.tanyinghao.model.dto.CodeDTO;
import com.tanyinghao.model.dto.QqLoginDTO;
import com.tanyinghao.model.vo.QqUserInfoVO;
import com.tanyinghao.model.vo.SocialTokenVO;
import com.tanyinghao.model.vo.SocialUserInfoVO;
import com.tanyinghao.model.vo.TokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.tanyinghao.comm.constant.SocialLoginConstant.*;
import static com.tanyinghao.comm.constant.SocialLoginConstant.CODE;

/**
 * @ClassName QqLoginStrategyImpl
 * @Description qq登录策略实现类
 * @Author 谭颍豪
 * @Date 2024/5/8 21:04
 * @Version 1.0
 **/
@Service("qqLoginStrategyImpl")
public class QqLoginStrategyImpl extends AbstractLoginStrategyImpl{

    @Autowired
    private QqProperties qqProperties;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public SocialTokenVO getSocialToken(CodeDTO data) {
        TokenVO qqToken = getQqToken(data.getCode());
        String userOpenId = getUserOpenId(qqToken.getAccess_token());
        return SocialTokenVO.builder()
                .openId(userOpenId)
                .accessToken(qqToken.getAccess_token())
                .loginType(LoginTypeEnum.QQ.getLoginType())
                .build();
    }

    @Override
    public SocialUserInfoVO getSocialUserInfo(SocialTokenVO socialTokenVO) {
        Map<String, String> data = new HashMap<>(3);
        data.put(QQ_OPEN_ID, socialTokenVO.getOpenId());
        data.put(ACCESS_TOKEN, socialTokenVO.getAccessToken());
        data.put(OAUTH_CONSUMER_KEY, qqProperties.getAppId());
        // 获取qq返回的用户信息
        QqUserInfoVO qqUserInfoVO = JSON.parseObject(restTemplate.getForObject(qqProperties.getUserInfoUrl(), String.class, data), QqUserInfoVO.class);

        return SocialUserInfoVO.builder()
                .id(socialTokenVO.getOpenId())
                .nickname(Objects.requireNonNull(qqUserInfoVO).getNickname())
                .avatar(qqUserInfoVO.getFigureurl_qq_1())
                .build();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 获取qq的token
     * @Date 21:47 2024/5/8
     * @Param [code]
     * @return com.tanyinghao.model.vo.TokenVO
     **/
    private TokenVO getQqToken(String code) {
        HashMap<String, String> qqData = new HashMap<>(5);

        qqData.put(GRANT_TYPE, qqProperties.getGrantType());
        qqData.put(CLIENT_ID, qqProperties.getAppId());
        qqData.put(CLIENT_SECRET, qqProperties.getAppKey());
        qqData.put(REDIRECT_URI, qqProperties.getRedirectUrl());
        qqData.put(CODE, code);

        try {
            return restTemplate.getForObject(qqProperties.getAccessTokenUrl(),
                    TokenVO.class,
                    qqData);
        } catch (Exception e) {
            throw new ServiceException("QQ登录错误");
        }

    }

    /**
     *
     * @Author TanYingHao
     * @Description 获取用户的OpenId
     * @Date 22:43 2024/5/8
     * @Param [accessToken]
     * @return java.lang.String
     **/
    private String getUserOpenId(String accessToken) {
        HashMap<String, String> dataMap = new HashMap<>(1);
        // 请求参数
        dataMap.put(ACCESS_TOKEN, accessToken);
        // 返回用户OpenId
        QqLoginDTO qqLoginDTO = restTemplate.getForObject(qqProperties.getUserOpenIdUrl(), QqLoginDTO.class, dataMap);
        return qqLoginDTO.getOpenid();
    }
}
