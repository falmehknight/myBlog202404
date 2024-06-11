package com.tanyinghao.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName OssProperties
 * @Description 阿里云oss
 * @Author 谭颍豪
 * @Date 2024/6/11 15:08
 * @Version 1.0
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "upload.oss")
public class OssProperties {

    /**
     * oss域名
     */
    private String url;

    /**
     * 终点
     */
    private String endpoint;

    /**
     * 访问密钥id
     */
    private String accessKeyId;

    /**
     * 访问密钥密码
     */
    private String accessKeySecret;

    /**
     * bucket名称
     */
    private String bucketName;
}
