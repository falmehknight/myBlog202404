package com.tanyinghao.comm.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BeanCopyUtils
 * @Description 拷贝工具类
 * @Author 谭颍豪
 * @Date 2024/6/9 10:23
 * @Version 1.0
 **/
public class BeanCopyUtils {

    public static <T> T copyBean(Object source, Class<T> target) {
        // 目标对象
        T result = null;
        try {
            result = target.getDeclaredConstructor().newInstance();
            if (source != null) {
                // 实现属性的copy
                BeanUtils.copyProperties(source, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回结果
        return result;
    }

    public static <T,S> List<T> copyBeanList(List<S> source, Class<T> target) {
        List<T> list = new ArrayList<>();
        if (null != source && source.size() > 0) {
            for (Object obj : source) {
                list.add(copyBean(obj,target));
            }
        }
        return list;
    }
}
