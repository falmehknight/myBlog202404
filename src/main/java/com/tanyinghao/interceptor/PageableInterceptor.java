package com.tanyinghao.interceptor;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanyinghao.comm.utils.PageUtils;
import org.checkerframework.checker.units.qual.N;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import java.util.Optional;

import static com.tanyinghao.comm.constant.PageConstant.*;

/**
 * @ClassName PageableInterceptor
 * @Description 分页拦截器
 * @Author 谭颍豪
 * @Date 2024/6/7 16:45
 * @Version 1.0
 **/
public class PageableInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        String currentPage = request.getParameter(CURRENT);
        String pageSize = Optional.ofNullable(request.getParameter(SIZE)).orElse(DEFAULT_SIZE);
        if (StringUtils.hasText(currentPage)) {
            PageUtils.setCurrentPage(new Page<>(Long.parseLong(currentPage), Long.parseLong(pageSize)));
        }
        return true;
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) throws Exception {
        PageUtils.remove();
    }
}
