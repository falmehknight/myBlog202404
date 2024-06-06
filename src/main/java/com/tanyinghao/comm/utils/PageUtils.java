package com.tanyinghao.comm.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Objects;

/**
 * @ClassName PageUtils
 * @Description 分页工具类
 * @Author 谭颍豪
 * @Date 2024/6/6 15:37
 * @Version 1.0
 **/
public class PageUtils {
    private static final ThreadLocal<Page<?>> PAGE_HOLDER = new ThreadLocal<>();

    public static void setCurrentPage(Page<?> page) {
        PAGE_HOLDER.set(page);
    }

    public static Page<?> getPage() {
        Page<?> page = PAGE_HOLDER.get();
        if (Objects.isNull(page)) {
            setCurrentPage(new Page<>());
        }
        return PAGE_HOLDER.get();
    }

    // 获取页码
    public static Long getCurrent() {
        return getPage().getCurrent();
    }

    // 获取一页的最大大小
    public static Long getSize() {
        return getPage().getSize();
    }

    public static Long getLimit() {
        return (getCurrent() - 1) * getSize();
    }

    public static void remove() {
        PAGE_HOLDER.remove();
    }
}
