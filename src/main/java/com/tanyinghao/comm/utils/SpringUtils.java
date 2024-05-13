package com.tanyinghao.comm.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;


/**
 * @ClassName SpringUtils
 * @Description spring工具类,获取Bean
 * @Author 谭颍豪
 * @Date 2024/5/13 20:46
 * @Version 1.0
 **/
@Component
public class SpringUtils implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        SpringUtils.beanFactory = configurableListableBeanFactory;
    }

    /**
     *
     * @Author TanYingHao
     * @Description 根据name获取对象
     * @Date 20:49 2024/5/13
     **/
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) beanFactory.getBean(name);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 根据Class获取对象
     * @Date 20:52 2024/5/13
     **/
    public static <T> T getBean(Class<T> tClass) throws BeansException {
        return beanFactory.getBean(tClass);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 根据名字获取对应class Type
     * @Date 20:53 2024/5/13
     **/
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.getType(name);
    }

}
