package com.tanyinghao.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName GiteeProperties
 * @Description gitee的属性类
 * @Author 谭颍豪
 * @Date 2024/5/7 23:34
 * @Version 1.0
 **/
@Configuration
@Data
@ConfigurationProperties(prefix = "oauth.gitee")
public class GiteeProperties {

    /**
     * clientId
     */
    private String clientId;

    /**
     * clientSecret
     */
    private String clientSecret;

    /**
     * Gitee登录类型
     */
    private String grantType;

    /**
     * Gitee回调域名
     */
    private String redirectUrl;

    /**
     * Gitee访问令牌地址
     */
    private String accessTokenUrl;

    /**
     * Gitee用户信息地址
     */
    private String userInfoUrl;
}
