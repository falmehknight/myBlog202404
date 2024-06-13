package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.entity.SiteConfig;
import org.springframework.web.multipart.MultipartFile;

public interface SiteConfigService extends IService<SiteConfig> {

    /**
     *
     * @Author TanYingHao
     * @Description 获取网站配置
     * @Date 11:28 2024/6/13
     * @Param []
     * @return com.tanyinghao.model.entity.SiteConfig
     **/
    SiteConfig getSiteConfig();

    /**
     *
     * @Author TanYingHao
     * @Description 更新网站配置
     * @Date 11:37 2024/6/13
     * @Param [siteConfig]
     **/
    void updateSiteConfig(SiteConfig siteConfig);

    /**
     *
     * @Author TanYingHao
     * @Description 上传网站配置图片
     * @Date 11:38 2024/6/13
     * @Param [file]
     * @return java.lang.String
     **/
    String uploadSiteImg(MultipartFile file);
}
