package com.tanyinghao.service;

import org.springframework.web.multipart.MultipartFile;

public interface BiliService {
    /**
     *
     * @Author TanYingHao
     * @Description B站图片上传
     * @Date 15:11 2024/6/15
     * @Param [file, csrf, data]
     * @return java.lang.Object
     **/
    String uploadBiliPicture(MultipartFile file, String csrf, String data);
}
