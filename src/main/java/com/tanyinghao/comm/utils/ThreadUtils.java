package com.tanyinghao.comm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @ClassName ThreadUtils
 * @Description 线程池工具类
 * @Author 谭颍豪
 * @Date 2024/5/13 21:46
 * @Version 1.0
 **/
public class ThreadUtils {

    private static final long OVERTIME = 120;

    private static final Logger logger = LoggerFactory.getLogger(ThreadUtils.class);
    /**
     *
     * @Author TanYingHao
     * @Description 停止线程池,①先使用shutdown，停止接受新任务并尝试完成所有已存在的任务。
     * ②如果超时，则调用shutdownNow，取消workQueue中的任务，并中断所有阻塞函数
     * ③如果仍然存在超时，则强制退出
     * ④另外再shutdown时线程本身本身被调用中断做了处理
     * @Date 21:49 2024/5/13
     * @Param [pool]
     **/
    public static void shutdownAndAwaitTermination(ExecutorService pool) {
        if (pool != null && !pool.isShutdown()) {
            pool.shutdown();
            try {
                if (!pool.awaitTermination(OVERTIME, TimeUnit.SECONDS)) {
                    pool.shutdownNow();
                    if (!pool.awaitTermination(OVERTIME, TimeUnit.SECONDS)) {
                        logger.info("Pool did not terminate");
                    }
                }
            } catch (InterruptedException e) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     *
     * @Author TanYingHao
     * @Description 打印线程异常信息
     * @Date 21:58 2024/5/13
     * @Param [runnable, throwable]
     **/
    public static void printException(Runnable runnable, Throwable throwable) {
        if (throwable == null && runnable instanceof Future<?>) {
            Future<?> future = (Future<?>) runnable;
            try {
                if (future.isDone()) {
                    future.get();
                }
            } catch (CancellationException e) {
                throwable = e;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                throwable = e.getCause();
            }
        }
        if ( throwable != null) {
            logger.error(throwable.getMessage(), throwable);
        }
    }

}
