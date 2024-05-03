package com.tanyinghao.comm.utils;

import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * @ClassName IpUtils
 * @Description ip工具类
 * @Author 谭颍豪
 * @Date 2024/5/3 17:35
 * @Version 1.0
 **/
public class IpUtils {

    private static Searcher searcher;

    /**
     *
     * @Author TanYingHao
     * @Description 根据ip从ip2region 中获取地理位置
     * @Date 17:43 2024/5/3
     * @Param [ip]
     * @return java.lang.String
     **/
    public static String getIpSource(String ip) {
        try {
            String address = searcher.searchByStr(ip);
            if (StringUtils.hasText(address)) {
                address = address.replace("|0","");
                address = address.replace("0|","");
            }
            return address;
        } catch (Exception e) {
            return "";
        }
    }
    /**
     *
     * @Author TanYingHao
     * @Description 在Nginx等代理之后获取用户真实IP地址
     * @Date 17:47 2024/5/3
     * @Param [request]
     * @return java.lang.String
     **/
    public static String getIpAddress(HttpServletRequest request) {
        String ip;
        // 记录真实ip
        try {
            ip = request.getHeader("X-Real-IP");
            // 如果没记录到真实地址，则按序尝试记录上一跳的地址
            if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                request.getHeader("x-forwarded-for");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            // 如果是本地主机
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
                    //根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        throw new UnknownHostException("无法确定主机的IP地址");
                    }
                    ip = inet.getHostAddress();
                }
            }
            // 使用代理，则获取第一个IP地址
            if (StringUtils.hasText(ip) && Objects.requireNonNull(ip).length() > 15) {
                int idx = ip.indexOf(",");
                if (idx > 0) {
                    ip = ip.substring(0, idx);
                }
            }
        } catch (Exception e) {
            ip = "";
        }
        return ip;
    }
}
