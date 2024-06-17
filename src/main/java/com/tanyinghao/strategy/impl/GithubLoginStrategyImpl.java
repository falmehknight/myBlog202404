package com.tanyinghao.strategy.impl;

import com.tanyinghao.comm.exception.ServiceException;
import com.tanyinghao.configuration.properties.GithubProperties;
import com.tanyinghao.model.dto.CodeDTO;
import com.tanyinghao.model.vo.GitUserInfoVO;
import com.tanyinghao.model.vo.SocialTokenVO;
import com.tanyinghao.model.vo.SocialUserInfoVO;
import com.tanyinghao.model.vo.TokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.tanyinghao.comm.constant.SocialLoginConstant.*;
import static com.tanyinghao.comm.enums.LoginTypeEnum.GITHUB;

/**
 * @ClassName GithubLoginStrategyImpl
 * @Description github登录策略实现类
 * @Author 谭颍豪
 * @Date 2024/5/8 21:05
 * @Version 1.0
 **/
@Service("githubLoginStrategyImpl")
public class GithubLoginStrategyImpl extends AbstractLoginStrategyImpl{

    @Autowired
    private GithubProperties githubProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public SocialTokenVO getSocialToken(CodeDTO data) {
        TokenVO githubToken = getGithubToken(data.getCode());
        return SocialTokenVO.builder()
                .accessToken(githubToken.getAccess_token())
                .loginType(GITHUB.getLoginType())
                .build();
    }

    @Override
    public SocialUserInfoVO getSocialUserInfo(SocialTokenVO socialTokenVO) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + socialTokenVO.getAccessToken());
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(null, headers);
        GitUserInfoVO gitUserInfoVO = restTemplate.exchange(githubProperties.getUserInfoUrl(),
                HttpMethod.GET,
                entity,
                GitUserInfoVO.class).getBody();
        return SocialUserInfoVO.builder()
                .avatar(Objects.requireNonNull(gitUserInfoVO).getAvatar_url())
                .id(gitUserInfoVO.getId())
                .nickname(gitUserInfoVO.getName())
                .build();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 获取github的token
     * @Date 21:27 2024/5/8
     * @Param [code]
     * @return com.tanyinghao.model.vo.TokenVO
     **/
    private TokenVO getGithubToken(String code) {
        LinkedMultiValueMap<String, String> githubData = new LinkedMultiValueMap<>();
        // Github的Token请求参数
        githubData.add(CLIENT_ID, githubProperties.getClientId());
        githubData.add(CLIENT_SECRET, githubProperties.getClientSecret());
        githubData.add(REDIRECT_URI, githubProperties.getRedirectUrl());
        githubData.add(CODE, code);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(githubData, headers);
        try {
            return restTemplate.exchange(githubProperties.getAccessTokenUrl(),
                    HttpMethod.POST,
                    httpEntity,
                    TokenVO.class).getBody();
        } catch (Exception e) {
            throw new ServiceException("Github登录错误");
        }
    }
}
