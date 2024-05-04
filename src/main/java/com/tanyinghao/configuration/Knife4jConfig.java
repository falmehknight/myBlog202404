package com.tanyinghao.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Collections;

/**
 * @ClassName Knife4jConfig
 * @Description Knife4j配置
 * @Author 谭颍豪
 * @Date 2024/5/4 17:29
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {
    @Value("${swagger.url}")
    private String host;

    /**
     * API接口包路径
     */
    @Value("${swagger.base-package}")
    private String basePackage;

    /**
     * API页面标题
     */
    @Value("${swagger.title}")
    private String title;

    /**
     * API描述
     */
    @Value("${swagger.description}")
    private String description;

    /**
     * 服务条款地址
     */
    @Value("${swagger.termsOfServiceUrl}")
    private String termsOfServiceUrl;

    /**
     * 版本号
     */
    @Value("${swagger.version}")
    private String version;

    /**
     * 联系人
     */
    @Value("${swagger.contact}")
    private Contact contact;

    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(Collections.singleton("https"))
                .host(host)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(contact)
                .version(version)
                .build();
    }
}
