package com.tanyinghao.interceptor;

import com.alibaba.fastjson2.JSON;
import com.tanyinghao.comm.annotation.AccessLimit;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.comm.utils.IpUtils;
import com.tanyinghao.comm.utils.WebUtils;
import com.tanyinghao.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AccessLimitInterceptor
 * @Description redis拦截器
 * @Author 谭颍豪
 * @Date 2024/5/3 17:09
 * @Version 1.0
 **/
@Component
@Slf4j
public class AccessLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        // handler是否是HandleMethod的实例
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
            // 如果没有访问控制的注解，注解通过
            if (accessLimit != null) {
                int seconds = accessLimit.seconds();
                int maxCount = accessLimit.maxCount();
                String ip = IpUtils.getIpAddress(request);
                String method = request.getMethod();
                String requestURI = request.getRequestURI();
                String redisKey = ip + ":" + method + ":" + requestURI;
                try {
                    Long count = redisService.incr(redisKey, 1L);
                    // 第一次访问
                    if (Objects.nonNull(count) && count == 1) {
                        redisService.setExpire(redisKey,seconds, TimeUnit.SECONDS);
                    } else if (count > maxCount) {
                        WebUtils.renderString(response, JSON.toJSONString(Result.fail(accessLimit.msg())));
                        log.warn(redisKey + "请求次数超过每" + seconds + "秒" + maxCount + "次");
                        result = false;
                    }
                } catch (RedisConnectionFailureException e) {
                    log.error("redis错误:" + e.getMessage());
                    result = false;
                }

            }
        }
        return result;
    }
}
