package com.tanyinghao.strategy;

import org.springframework.web.multipart.MultipartFile;

public interface UploadStrategy {

    /**
     *
     * @Author TanYingHao
     * @Description 上传文件
     * @Date 10:43 2024/6/11
     * @Param [file, path]
     * @return java.lang.String
     **/
    String uploadFile(MultipartFile file, String path);
}
