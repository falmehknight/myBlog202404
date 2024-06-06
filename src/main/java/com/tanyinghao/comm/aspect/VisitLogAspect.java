package com.tanyinghao.comm.aspect;

import com.tanyinghao.comm.annotation.VisitLogger;
import com.tanyinghao.comm.utils.IpUtils;
import com.tanyinghao.comm.utils.UserAgentUtils;
import com.tanyinghao.manager.AsyncManager;
import com.tanyinghao.manager.factory.AsyncFactory;
import com.tanyinghao.model.entity.VisitLog;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName VisitLogAspect
 * @Description AOP记录访问日志
 * @Author 谭颍豪
 * @Date 2024/5/16 19:06
 * @Version 1.0
 **/
@Aspect
@Component
public class VisitLogAspect {

    @Pointcut("@annotation(com.tanyinghao.comm.annotation.VisitLogger)")
    public void visitLogPointCut() {

    }

    @AfterReturning(value = "visitLogPointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        // 获取植入点的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点的方法
        Method method = signature.getMethod();
        // 获取操作
        VisitLogger visitLogger = method.getAnnotation(VisitLogger.class);
        // 获取request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        VisitLog visitLog = new VisitLog();
        String ipAddress = IpUtils.getIpAddress(request);
        String ipSource = IpUtils.getIpSource(ipAddress);
        Map<String, String> stringMap = UserAgentUtils.parseOsAndBrowser(request.getHeader("User-Agent"));
        visitLog.setIpAddress(ipAddress);
        visitLog.setIpSource(ipSource);
        visitLog.setOs(stringMap.get("os"));
        visitLog.setBrowser(stringMap.get("browser"));
        visitLog.setPage(visitLogger.value());
        // 保存到数据库中
        AsyncManager.getInstance().execute(AsyncFactory.recordVisit(visitLog));
    }
}
