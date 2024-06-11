package com.tanyinghao.strategy.impl;

import com.tanyinghao.comm.exception.ServiceException;
import com.tanyinghao.comm.utils.FileUtils;
import com.tanyinghao.strategy.UploadStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName AbstractUploadStrategyImpl
 * @Description 抽象上传模板
 * @Author 谭颍豪
 * @Date 2024/6/11 11:05
 * @Version 1.0
 **/
@Service
public abstract class AbstractUploadStrategyImpl implements UploadStrategy {


    @Override
    public String uploadFile(MultipartFile file, String path) {
        try {
            // 获取文件的md5值
            String md5 = FileUtils.getMd5(file.getInputStream());
            // 获取文件扩展名
            String extension = FileUtils.getExtension(file);
            // 重新生成文件名
            String fileName = md5 + "." + extension;
            // 判断文件是否已存在
            if (!exists(path + fileName)) {
                // 不存在则继续上传
                upload(path, fileName, file.getInputStream());
            }
            // 返回文件访问路径
            return getFileAccessUrl(path + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("文件上传失败");
        }
    }

    /**
     *
     * @Author TanYingHao
     * @Description 判断文件是否存在
     * @Date 11:24 2024/6/11
     * @Param [filePath] 文件路径
     * @return java.lang.Boolean
     **/
    public abstract Boolean exists(String filePath);

    /**
     * 上传
     *
     * @param path        路径
     * @param fileName    文件名
     * @param inputStream 输入流
     * @throws IOException io异常
     */
    public abstract void upload(String path, String fileName, InputStream inputStream) throws IOException;

    /**
     * 获取文件访问url
     *
     * @param filePath 文件路径
     * @return {@link String} 文件url
     */
    public abstract String getFileAccessUrl(String filePath);
}
