package com.tanyinghao.comm.annotation;

import java.lang.annotation.*;

/**
 *
 * @Author TanYingHao
 * @Description 操作日志注解
 * @Date 19:30 2024/5/13
 **/

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OptLogger {

    /**
     *
     * @Author TanYingHao
     * @Description 操作描述
     * @Date 19:31 2024/5/13
     **/
    String value() default "";
}
