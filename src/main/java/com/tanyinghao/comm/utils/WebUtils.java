package com.tanyinghao.comm.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName WebUtils
 * @Description 前端返回工具
 * @Author 谭颍豪
 * @Date 2024/5/3 18:23
 * @Version 1.0
 **/
public class WebUtils {

    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(string);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
