package com.tanyinghao.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName GithubProperties
 * @Description gitHub登录属性
 * @Author 谭颍豪
 * @Date 2024/5/8 20:58
 * @Version 1.0
 **/
@Configuration
@Data
@ConfigurationProperties(prefix = "oauth.github")
public class GithubProperties {

    /**
     * clientId
     */
    private String clientId;

    /**
     * clientSecret
     */
    private String clientSecret;

    /**
     * Github回调域名
     */
    private String redirectUrl;

    /**
     * Github访问令牌地址
     */
    private String accessTokenUrl;

    /**
     * Github用户信息地址
     */
    private String userInfoUrl;
}
