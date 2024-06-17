package com.tanyinghao.strategy.impl;

import com.tanyinghao.comm.exception.ServiceException;
import com.tanyinghao.configuration.properties.GiteeProperties;
import com.tanyinghao.model.dto.CodeDTO;
import com.tanyinghao.model.vo.GitUserInfoVO;
import com.tanyinghao.model.vo.SocialTokenVO;
import com.tanyinghao.model.vo.SocialUserInfoVO;
import com.tanyinghao.model.vo.TokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.tanyinghao.comm.constant.SocialLoginConstant.*;
import static com.tanyinghao.comm.enums.LoginTypeEnum.GITEE;

/**
 * @ClassName GiteeLoginStrategyImpl
 * @Description gitee登录策略实现类
 * @Author 谭颍豪
 * @Date 2024/5/7 23:33
 * @Version 1.0
 **/
@Service("giteeLoginStrategyImpl")
public class GiteeLoginStrategyImpl extends AbstractLoginStrategyImpl{

    @Autowired
    private GiteeProperties giteeProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public SocialTokenVO getSocialToken(CodeDTO data) {
        TokenVO giteeToken = getGiteeToken(data.getCode());
        return SocialTokenVO.builder()
                .accessToken(giteeToken.getAccess_token())
                .loginType(GITEE.getLoginType())
                .build();
    }

    @Override
    public SocialUserInfoVO getSocialUserInfo(SocialTokenVO socialTokenVO) {
        Map<String,String> dataMap = new HashMap<>(1);
        dataMap.put(ACCESS_TOKEN, socialTokenVO.getAccessToken());
        GitUserInfoVO gitUserInfoVO = restTemplate.getForObject(giteeProperties.getUserInfoUrl(), GitUserInfoVO.class, dataMap);
        // 返回用户信息
        return SocialUserInfoVO.builder()
                .avatar(Objects.requireNonNull(gitUserInfoVO).getAvatar_url())
                .id(gitUserInfoVO.getId())
                .nickname(gitUserInfoVO.getName())
                .build();
    }


    /**
     *
     * @Author TanYingHao
     * @Description 获取gitee的token
     * @Date 23:43 2024/5/7
     * @Param [code] 第三方的code
     * @return com.tanyinghao.model.vo.TokenVO
     **/
    private TokenVO getGiteeToken(String code) {
        LinkedMultiValueMap<String, String> giteeData = new LinkedMultiValueMap<>();
        // Gitee的Token请求参数
        giteeData.add(CLIENT_ID, giteeProperties.getClientId());
        giteeData.add(CLIENT_SECRET, giteeProperties.getClientSecret());
        giteeData.add(GRANT_TYPE, giteeProperties.getGrantType());
        giteeData.add(REDIRECT_URI, giteeProperties.getRedirectUrl());
        giteeData.add(CODE, code);
        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(giteeData, null);
        try {
            return restTemplate.exchange(giteeProperties.getAccessTokenUrl(), HttpMethod.POST, httpEntity, TokenVO.class).getBody();
        } catch (Exception e) {
            throw new ServiceException("Gitee登录错误");
        }
    }
}
