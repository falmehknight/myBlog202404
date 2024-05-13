package com.tanyinghao.manager;


import com.tanyinghao.comm.utils.SpringUtils;
import com.tanyinghao.comm.utils.ThreadUtils;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AsyncManager
 * @Description 异步任务管理器
 * @Author 谭颍豪
 * @Date 2024/5/13 20:39
 * @Version 1.0
 **/
public class AsyncManager {
    // 单例模式，这里采用饿汉式
    private AsyncManager() {
    }
    // 饿汉式，在类加载的时候立即进行初始化
    private static final AsyncManager INSTANCE = new AsyncManager();

    public static AsyncManager getInstance() {
        return INSTANCE;
    }

    /**
     * 异步操作任务调度线程池
     */
    private final ScheduledExecutorService executor = SpringUtils.getBean("scheduleExecutorService");
    /**
     *
     * @Author TanYingHao
     * @Description 执行任务
     * @Date 21:45 2024/5/13
     **/
    public void execute(TimerTask task) {
        executor.schedule(task, 10, TimeUnit.MILLISECONDS);
    }
    /**
     *
     * @Author TanYingHao
     * @Description 停止线程池
     * @Date 21:46 2024/5/13
     **/
    public void shutdown() {
        ThreadUtils.shutdownAndAwaitTermination(executor);
    }


}
