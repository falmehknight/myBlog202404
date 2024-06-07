package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.RoleDTO;
import com.tanyinghao.model.dto.RoleStatusDTO;
import com.tanyinghao.model.entity.Role;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.RoleVO;

import java.util.List;

public interface RoleService extends IService<Role> {

    /**
     *
     * @Author TanYingHao
     * @Description 查看角色列表
     * @Date 10:22 2024/6/7
     * @Param [condition]
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.RoleVO>
     **/
    PageResult<RoleVO> listRoleVO(ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 添加角色
     * @Date 10:23 2024/6/7
     * @Param [role]
     * @return void
     **/
    void addRole(RoleDTO role);

    /**
     *
     * @Author TanYingHao
     * @Description 删除角色
     * @Date 10:24 2024/6/7
     * @Param [roleIdList]
     * @return void
     **/
    void deleteRole(List<String> roleIdList);

    /**
     *
     * @Author TanYingHao
     * @Description 更新角色
     * @Date 10:25 2024/6/7
     * @Param [role]
     * @return void
     **/
    void updateRole(RoleDTO role);

    /**
     *
     * @Author TanYingHao
     * @Description 修改角色状态
     * @Date 10:26 2024/6/7
     * @Param [roleStatus]
     * @return void
     **/
    void updateRoleStatus(RoleStatusDTO roleStatus);

    /**
     *
     * @Author TanYingHao
     * @Description 查看角色的菜单权限
     * @Date 10:27 2024/6/7
     * @Param [roleId]
     * @return java.util.List<java.lang.Integer>
     **/
    List<Integer> listRoleMenuTree(String roleId);
}
