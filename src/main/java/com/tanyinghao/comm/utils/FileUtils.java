package com.tanyinghao.comm.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @ClassName FileUtils
 * @Description 文件工具类
 * @Author 谭颍豪
 * @Date 2024/6/11 11:07
 * @Version 1.0
 **/
@Log4j2
public class FileUtils {

    /**
     *
     * @Author TanYingHao
     * @Description 获取文件md5值
     * @Date 11:09 2024/6/11
     * @Param [inputStream]
     * @return java.lang.String
     **/
    public static String getMd5(InputStream inputStream) {
        String md5 = null;
        try {
            md5 = DigestUtils.md5DigestAsHex(inputStream);
        } catch (Exception e) {
            log.error("get md5 error, {}", e.getMessage());
        }
        return md5;
    }


    /**
     *
     * @Author TanYingHao
     * @Description 获取文件名后缀
     * @Date 11:09 2024/6/11
     * @Param [file]
     * @return java.lang.String
     **/
    public static String getExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension)) {
            extension = MimeTypeUtils.getExtension(Objects.requireNonNull(file.getContentType()));
        }
        return extension;
    }

    /**
     *
     * @Author TanYingHao
     * @Description 转换file
     * @Date 11:16 2024/6/11
     * @Param [multipartFile]
     * @return java.io.File
     **/
    public static File multipartFileToFile(MultipartFile multipartFile) {
        File tempFile = null;
        try {
            // 获取文件md5值
            String md5 = getMd5(multipartFile.getInputStream());
            // 获取文件扩展名
            String extName = getExtension(multipartFile);
            // 重新生成文件名
            String fileName = md5 + extName;
            // 创建临时文件
            tempFile = File.createTempFile(fileName, extName);
            multipartFile.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }

    /**
     *
     * @Author TanYingHao
     * @Description 创建目录
     * @Date 11:18 2024/6/11
     * @Param [file]
     * @return boolean
     **/
    public static boolean mkdir(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return false;
        }
        return file.mkdirs();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 删除文件
     * @Date 11:19 2024/6/11
     * @Param [src]
     * @return void
     **/
    public static void deleteFile(File src) {
        for (File file : src.listFiles()) {
            if (file.isFile()) {
                file.delete();
            } else {
                deleteFile(file);
            }
        }
        src.delete();
    }
}
