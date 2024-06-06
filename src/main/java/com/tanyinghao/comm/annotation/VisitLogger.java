package com.tanyinghao.comm.annotation;

import java.lang.annotation.*;

/**
 *
 * @Author TanYingHao
 * @Description 访问日志注解
 * @Date 19:03 2024/5/16
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisitLogger {

    /**
     *
     * @Author TanYingHao
     * @Description 访问页面
     * @Date 19:05 2024/5/16
     **/
    String value() default "";
}
