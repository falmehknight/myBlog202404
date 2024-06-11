package com.tanyinghao.strategy.context;

import com.tanyinghao.comm.enums.UploadModeEnum;
import com.tanyinghao.strategy.UploadStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @ClassName UploadStrategyContext
 * @Description 上传策略上下文
 * @Author 谭颍豪
 * @Date 2024/6/11 11:44
 * @Version 1.0
 **/
@Service
public class UploadStrategyContext {

    @Value("${upload.strategy}")
    private String uploadStrategy;

    @Autowired
    private Map<String, UploadStrategy> uploadStrategyMap;

    /**
     *
     * @Author TanYingHao
     * @Description 上传文件
     * @Date 11:59 2024/6/11
     * @Param [file, path] 文件，路径
     * @return java.lang.String 文件地址
     **/
    public String executeUploadStrategy(MultipartFile file, String path) {
        return uploadStrategyMap.get(UploadModeEnum.getStrategy(uploadStrategy)).uploadFile(file, path);
    }
}
