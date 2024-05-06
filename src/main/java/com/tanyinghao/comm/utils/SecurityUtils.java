package com.tanyinghao.comm.utils;

import cn.dev33.satoken.secure.SaSecureUtil;
import org.thymeleaf.util.StringUtils;

/**
 * @ClassName SecurityUtils
 * @Description 密码加密工具类
 * @Author 谭颍豪
 * @Date 2024/5/6 23:16
 * @Version 1.0
 **/
public class SecurityUtils {


    /**
     *
     * @Author TanYingHao
     * @Description 校验密码
     * @Date 23:18 2024/5/6
     * @Param [pw1, pw2]
     * @return boolean
     **/

    public static boolean checkPw(String pw1, String pw2) {
        String encrypt = sha256Encrypt(pw2);
        return StringUtils.equals(encrypt, pw1);
    }

    /**
     *
     * @Author TanYingHao
     * @Description sha256加密
     * @Date 23:18 2024/5/6
     * @Param [pw]
     * @return java.lang.String
     **/
    public static String sha256Encrypt(String pw) {
        return SaSecureUtil.sha256(pw);
    }
}
