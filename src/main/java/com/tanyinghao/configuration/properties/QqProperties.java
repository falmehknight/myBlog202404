package com.tanyinghao.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName QqProperties
 * @Description qq属性配置类
 * @Author 谭颍豪
 * @Date 2024/5/8 21:00
 * @Version 1.0
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "oauth.qq")
public class QqProperties {
    /**
     * QQ appId
     */
    private String appId;

    /**
     * QQ clientSecret
     */
    private String appKey;

    /**
     * QQ登录类型
     */
    private String grantType;

    /**
     * QQ回调域名
     */
    private String redirectUrl;

    /**
     * QQ访问令牌地址
     */
    private String accessTokenUrl;

    /**
     * 用户OpenID获取地址
     */
    private String userOpenIdUrl;

    /**
     * QQ用户信息地址
     */
    private String userInfoUrl;
}
