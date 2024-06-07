package com.tanyinghao.comm.satoken;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName StpInterfaceImpl
 * @Description 自定义权限验证接口扩展
 * @Author 谭颍豪
 * @Date 2024/6/7 8:06
 * @Version 1.0
 **/
@Component
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object o, String s) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return null;
    }
}
