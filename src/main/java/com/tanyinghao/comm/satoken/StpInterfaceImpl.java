package com.tanyinghao.comm.satoken;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.tanyinghao.mapper.MenuMapper;
import com.tanyinghao.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     *
     * @Author TanYingHao
     * @Description 返回一个账号所拥有的权限码集合
     * @Date 16:20 2024/6/7
     * @Param [o, s]
     * @return java.util.List<java.lang.String>
     **/
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 权限码集合
        List<String> permissionList = new ArrayList<>();
        // 遍历角色列表，查询拥有的权限码
        for (String roleId : getRoleList(loginId, loginType)) {
            SaSession sessionById = SaSessionCustomUtil.getSessionById("role-" + roleId);
            List<String> list = sessionById.get("Permission_List", () -> menuMapper.selectPermissionByRoleId(roleId));
            permissionList.addAll(list);
        }
        // 返回权限集集合
        return permissionList;
    }

    /**
     *
     * @Author TanYingHao
     * @Description 返回一个账号所拥有的可用角色标识集合
     * @Date 16:37 2024/6/7
     * @Param [loginId, loginType]
     * @return java.util.List<java.lang.String>
     **/
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        SaSession session = StpUtil.getSessionByLoginId(loginId);
        return session.get("Role_List", () -> roleMapper.selectRoleListByUserId(loginId));
    }
}
