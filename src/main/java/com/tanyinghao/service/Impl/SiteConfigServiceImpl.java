package com.tanyinghao.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.comm.utils.FileUtils;
import com.tanyinghao.mapper.BlogFileMapper;
import com.tanyinghao.mapper.SiteConfigMapper;
import com.tanyinghao.model.entity.BlogFile;
import com.tanyinghao.model.entity.SiteConfig;
import com.tanyinghao.service.RedisService;
import com.tanyinghao.service.SiteConfigService;
import com.tanyinghao.strategy.context.UploadStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

import static com.tanyinghao.comm.constant.CommConstant.FALSE;
import static com.tanyinghao.comm.constant.RedisConstant.SITE_SETTING;
import static com.tanyinghao.comm.enums.FilePathEnum.CONFIG;

/**
 * @ClassName SiteConfigServiceImpl
 * @Description 网站配置服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/13 11:20
 * @Version 1.0
 **/
@Service
public class SiteConfigServiceImpl extends ServiceImpl<SiteConfigMapper, SiteConfig> implements SiteConfigService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private SiteConfigMapper siteConfigMapper;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Autowired
    private BlogFileMapper blogFileMapper;

    @Override
    public SiteConfig getSiteConfig() {
        SiteConfig siteConfig = redisService.getObject(SITE_SETTING);
        if (Objects.isNull(siteConfig)) {
            // 从数据库中获取
            siteConfig = siteConfigMapper.selectById(1);
            redisService.setObject(SITE_SETTING, siteConfig);
        }
        return siteConfig;
    }

    @Override
    public void updateSiteConfig(SiteConfig siteConfig) {
        baseMapper.updateById(siteConfig);
        redisService.deleteObject(SITE_SETTING);
    }

    @Override
    public String uploadSiteImg(MultipartFile file) {
        // 上传文件
        String url = uploadStrategyContext.executeUploadStrategy(file, CONFIG.getPath());
        try {
            String md5 = FileUtils.getMd5(file.getInputStream());
            String extension = FileUtils.getExtension(file);
            BlogFile existFile = blogFileMapper.selectOne(new LambdaQueryWrapper<BlogFile>()
                    .select(BlogFile::getId)
                    .eq(BlogFile::getFileName, md5)
                    .eq(BlogFile::getFilePath, CONFIG.getPath()));
            if (Objects.isNull(existFile)) {
                // 保存文件信息
                BlogFile newFile = BlogFile.builder()
                        .fileUrl(url)
                        .fileName(md5)
                        .filePath(CONFIG.getFilePath())
                        .extendName(extension)
                        .fileSize((int) file.getSize())
                        .isDir(FALSE)
                        .build();
                blogFileMapper.insert(newFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}
