package com.tanyinghao.comm.aspect;


import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSON;
import com.tanyinghao.comm.annotation.OptLogger;
import com.tanyinghao.comm.utils.IpUtils;
import com.tanyinghao.manager.AsyncManager;
import com.tanyinghao.manager.factory.AsyncFactory;
import com.tanyinghao.model.entity.OperationLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @ClassName OptLogAspect
 * @Description AOP记录操作日志
 * @Author 谭颍豪
 * @Date 2024/5/13 19:31
 * @Version 1.0
 **/
@Component
@Aspect
public class OptLogAspect {
    // 记录请求开始的时间
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     *
     * @Author TanYingHao
     * @Description 设置操作日志切入点,记录操作日志，在注解的位置切入代码
     * @Date 19:52 2024/5/13
     **/
    @Pointcut("@annotation(com.tanyinghao.comm.annotation.OptLogger)")
    public void optLOgPointCut() {
    }

    /**
     *
     * @Author TanYingHao
     * @Description 前置通知
     * @Date 19:55 2024/5/13
     **/
    @Before("optLOgPointCut()")
    public void doBefore() {
        // 记录请求开始的时间
        startTime.set(System.currentTimeMillis());
    }
    /**
     *
     * @Author TanYingHao
     * @Description 连接点正常返回通知，拦截用户操作日志，正常执行完成后操作，如果连接点抛出异常，则不会执行
     * @Date 19:56 2024/5/13
     * @Param [joinPoint, result] 切面方法的信息 返回结果
     * @return void
     **/
    @AfterReturning(value = "optLOgPointCut()", returning = "result")
    @SuppressWarnings(value = "unchecked")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        // 1. 利用反射获取织入点的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        var method = signature.getMethod();
        // 2. 获取操作,swagger的api和apiOperation 以及自定义的日志操作注解
        Api api = (Api) signature.getDeclaringType().getAnnotation(Api.class);
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        OptLogger optLogger = method.getAnnotation(OptLogger.class);
        // 3. 获取request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        // 4. 日志保存到数据库中
        OperationLog operationLog = new OperationLog();
        // 4.1 操作相关，操作模块和类型,操作描述
        operationLog.setModule(api.tags()[0]);
        operationLog.setType(optLogger.value());
        operationLog.setDescription(apiOperation.value());
        // 4.3 请求相关，请求URL,请求方法,请求参数,请求方式
        operationLog.setUri(request.getRequestURI());
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();
        operationLog.setName(className + "." + methodName);
        // 请求参数
        if (joinPoint.getArgs()[0] instanceof MultipartFile) {
            operationLog.setParams(((MultipartFile) joinPoint.getArgs()[0]).getOriginalFilename());
        } else {
            operationLog.setParams(JSON.toJSONString(joinPoint.getArgs()));
        }
        // 请求方式
        operationLog.setMethod(Objects.requireNonNull(request).getMethod());
        // 5.数据相关,返回数据，用户id，操作id和地址
        operationLog.setData(JSON.toJSONString(result));
        operationLog.setUserId(StpUtil.getLoginIdAsInt());
        operationLog.setNickname("flameHKnight");
        String ipAddress = IpUtils.getIpAddress(request);
        operationLog.setIpAddress(ipAddress);
        operationLog.setIpSource(IpUtils.getIpSource(ipAddress));
        // 执行耗时
        operationLog.setTimes(System.currentTimeMillis() - startTime.get());
        startTime.remove();
        // 异步保存到数据库中
        AsyncManager.getInstance().execute(AsyncFactory.recordOperation(operationLog));
    }
}
