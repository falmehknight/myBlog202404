package com.tanyinghao.strategy.impl;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.util.Auth;
import com.tanyinghao.configuration.properties.QiniuProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiniu.storage.UploadManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName QiniuUploadStrategyImpl
 * @Description 七牛云上传策略
 * @Author 谭颍豪
 * @Date 2024/6/11 14:43
 * @Version 1.0
 **/
@Service("qiniuUploadStrategyImpl")
@Slf4j
public class QiniuUploadStrategyImpl extends AbstractUploadStrategyImpl{

    @Autowired
    private QiniuProperties qiniuProperties;

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private Auth auth;

    @Override
    public Boolean exists(String filePath) {
        return false;
    }

    @Override
    public void upload(String path, String fileName, InputStream inputStream) throws IOException {
        try {
            if (path.length() > 1 && path.charAt(0) == '/') {
                path = path.substring(1);
            }
            // 上传图片文件
            Response res = uploadManager.put(inputStream, path + fileName, auth.uploadToken(qiniuProperties.getBucketName()), null, null);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛云失败：" + res.toString());
            }
        } catch (QiniuException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getFileAccessUrl(String filePath) {
        // 1 / -> /   /xxx/ -> xxx/
        if (filePath.length() > 1 && filePath.charAt(0) == '/') {
            filePath = filePath.substring(1);
        }
        return qiniuProperties.getUrl() + filePath;
    }
}
