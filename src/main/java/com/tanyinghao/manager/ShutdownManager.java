package com.tanyinghao.manager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @ClassName ShutdownManager
 * @Description 确保应用退出时能关闭后台线程
 * @Author 谭颍豪
 * @Date 2024/5/13 22:48
 * @Version 1.0
 **/
@Component
public class ShutdownManager {

    private static final Logger logger = LoggerFactory.getLogger(ShutdownManager.class);

    @PreDestroy // 此注解表示销毁bean之前执行
    public void destroy() {
        shutdownAsyncManager();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 停止异步执行的任务
     * @Date 22:53 2024/5/13
     **/
    private void shutdownAsyncManager() {
        try {
            logger.info("-----关闭后台任务线程池-----");
            AsyncManager.getInstance().shutdown();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
