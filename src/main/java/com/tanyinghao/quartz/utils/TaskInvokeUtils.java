package com.tanyinghao.quartz.utils;

import com.tanyinghao.comm.utils.SpringUtils;
import com.tanyinghao.model.entity.Task;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName TaskInvokeUtils
 * @Description 任务执行工具
 * @Author 谭颍豪
 * @Date 2024/6/13 20:05
 * @Version 1.0
 **/
public class TaskInvokeUtils {
    /**
     *
     * @Author TanYingHao
     * @Description 执行方法
     * @Date 20:22 2024/6/13
     * @Param [task] 系统任务
     **/
    public static void invokeMethod(Task task) throws Exception {
        String invokeTarget = task.getInvokeTarget();
        String beanName = getBeanName(invokeTarget);
        String methodName = getMethodName(invokeTarget);
        List<Object[]> methodParams = getMethodParams(invokeTarget);
        if (!isValidClassName(beanName)) {
            Object bean = SpringUtils.getBean(beanName);
            invokeMethod(bean, methodName, methodParams);
        } else {
            Object bean = Class.forName(beanName).getDeclaredConstructor().newInstance();
            invokeMethod(bean, methodName, methodParams);
        }
    }

    /**
     *
     * @Author TanYingHao
     * @Description 调用任务方法
     * @Date 22:55 2024/6/13
     * @Param [bean, methodName, methodParams] 目标对象，方法名称，方法参数
     **/
    private static void invokeMethod(Object bean, String methodName, List<Object[]> methodParams) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (methodParams != null && methodParams.size() > 0) {
            Method method = bean.getClass().getDeclaredMethod(methodName, getMethodParamsType(methodParams));
            method.invoke(bean, getMethodParamsValue(methodParams));
        } else {
            Method method = bean.getClass().getDeclaredMethod(methodName);
            method.invoke(bean);
        }
    }

    /**
     *
     * @Author TanYingHao
     * @Description 获取参数值
     * @Date 23:03 2024/6/13
     * @Param [methodParams] 参数相关列表
     * @return java.lang.Object[]
     **/
    private static Object[] getMethodParamsValue(List<Object[]> methodParams) {
        Object[] clazz = new Object[methodParams.size()];
        int index = 0;
        for (Object[] os : methodParams) {
            clazz[index] = os[0];
            index++;
        }
        return clazz;
    }

    /**
     *
     * @Author TanYingHao
     * @Description 获取参数类型
     * @Date 23:01 2024/6/13
     * @Param [methodParams] 参数相关列表
     * @return java.lang.Class<?>[]
     **/
    private static Class<?>[] getMethodParamsType(List<Object[]> methodParams) {
        Class<?>[] clazz = new Class<?>[methodParams.size()];
        int index = 0;
        for (Object[] os : methodParams) {
            clazz[index] = (Class<?>) os[1];
        }
        return clazz;
    }

    /**
     *
     * @Author TanYingHao
     * @Description 检验是否为class包名
     * @Date 22:52 2024/6/13
     * @Param [beanName]
     * @return boolean
     **/
    private static boolean isValidClassName(String beanName) {
        return StringUtils.countMatches(beanName, ".") > 1;
    }

    /**
     *
     * @Author TanYingHao
     * @Description 获取方法的参数列表
     * @Date 20:32 2024/6/13
     * @Param [invokeTarget]
     * @return java.util.List<java.lang.Object[]>
     **/
    private static List<Object[]> getMethodParams(String invokeTarget) {
        String methodStr = StringUtils.substringBetween(invokeTarget, "(", ")");
        if (StringUtils.isEmpty(methodStr)) {
            return null;
        }
        String[] methodParams = methodStr.split(",(?=([^\"']*[\"'][^\"']*[\"'])*[^\"']*$)");
        List<Object[]> clazz = new LinkedList<>();
        for (String methodParam : methodParams) {
            String str = StringUtils.trimToEmpty(methodParam);
            // String字符串类型，以“或”开头
            if (StringUtils.startsWithAny(str, "'", "\"")) {
                clazz.add(new Object[]{StringUtils.substring(str, 1, str.length() - 1), String.class});
            }
            // boolean布尔类型，等于true或者false
            else if ("true".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str)) {
                clazz.add(new Object[]{Boolean.valueOf(str), Boolean.class});
            }
            // long长整形，以L结尾
            else if (StringUtils.endsWith(str, "L")) {
                clazz.add(new Object[]{Long.valueOf(StringUtils.substring(str, 0, str.length() - 1)), Long.class});
            }
            // double浮点类型，以D结尾
            else if (StringUtils.endsWith(str, "D")) {
                clazz.add(new Object[]{Double.valueOf(StringUtils.substring(str, 0, str.length() - 1)), Double.class});
            }
            // 其他类型归类为整形
            else {
                clazz.add(new Object[]{Integer.valueOf(str), Integer.class});
            }
        }
        return clazz;
    }

    /**
     *
     * @Author TanYingHao
     * @Description 获取方法名字
     * @Date 20:28 2024/6/13
     * @Param [invokeTarget]
     * @return java.lang.String
     **/
    private static String getMethodName(String invokeTarget) {
        String methodName = StringUtils.substringAfterLast(invokeTarget, ".");
        return StringUtils.substringBefore(invokeTarget, "(");
    }

    /**
     *
     * @Author TanYingHao
     * @Description 获取bean的名称
     * @Date 20:24 2024/6/13
     * @Param [invokeTarget] 调用目标
     * @return java.lang.String
     **/
    private static String getBeanName(String invokeTarget) {
        String beanName = StringUtils.substringBefore(invokeTarget, "(");
        return StringUtils.substringBeforeLast(beanName, ".");
    }
}
