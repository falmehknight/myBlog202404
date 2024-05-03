package com.tanyinghao.comm.annotation;

import java.lang.annotation.*;

/**
 *
 * @Author TanYingHao
 * @Description redis限流注解
 * @Date 16:34 2024/5/3
 **/

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {

    /**
     *
     * @Author TanYingHao
     * @Description 限制周期/秒
     * @Date 16:34 2024/5/3
     **/
    int seconds();

    /**
     *
     * @Author TanYingHao
     * @Description 规定周期内限制次数
     * @Date 16:35 2024/5/3
     **/
    int maxCount();

    /**
     *
     * @Author TanYingHao
     * @Description 触发限制时的消息提示
     * @Date 16:36 2024/5/3
     **/
    String msg() default "操作过于频繁请稍后再试";
}
