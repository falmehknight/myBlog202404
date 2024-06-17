package com.tanyinghao.configuration;

import com.tanyinghao.comm.utils.ThreadUtils;
import com.tanyinghao.configuration.properties.ThreadPoolProperties;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName ThreadPoolConfig
 * @Description 线程池配置
 * @Author 谭颍豪
 * @Date 2024/6/17 9:54
 * @Version 1.0
 **/
@Configuration
public class ThreadPoolConfig {

    @Autowired
    private ThreadPoolProperties threadPoolProperties;

    /**
     *
     * @Author TanYingHao
     * @Description 线程池
     * @Date 9:59 2024/6/17
     * @Param []
     * @return org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
     **/
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程池大小
        executor.setCorePoolSize(threadPoolProperties.getCorePoolSize());
        // 最大线程数
        executor.setMaxPoolSize(threadPoolProperties.getMaxPoolSize());
        // 队列最大长度
        executor.setQueueCapacity(threadPoolProperties.getQueueCapacity());
        // 线程池维护线程所允许的空闲时间
        executor.setKeepAliveSeconds(threadPoolProperties.getKeepAliveSeconds());
        // 策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    @Bean(name = "scheduleExecutorService")
    protected ScheduledExecutorService scheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(threadPoolProperties.getCorePoolSize(),
                new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build(),
                new ThreadPoolExecutor.CallerRunsPolicy()) {

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                ThreadUtils.printException(r,t);
            }
        };
    }
}
