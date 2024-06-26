package com.tanyinghao.service.Impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.comm.utils.PageUtils;
import com.tanyinghao.mapper.RoleMapper;
import com.tanyinghao.mapper.RoleMenuMapper;
import com.tanyinghao.mapper.UserRoleMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.RoleDTO;
import com.tanyinghao.model.dto.RoleStatusDTO;
import com.tanyinghao.model.entity.Role;
import com.tanyinghao.model.entity.UserRole;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.RoleVO;
import com.tanyinghao.service.RoleService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.tanyinghao.comm.constant.CommConstant.ADMIN;
import static com.tanyinghao.comm.constant.CommConstant.TRUE;

/**
 * @ClassName RoleServiceImpl
 * @Description 角色服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/7 10:32
 * @Version 1.0
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public PageResult<RoleVO> listRoleVO(ConditionDTO condition) {
        // 查询角色数量
        Long count = roleMapper.selectCountRoleVO(condition);
        if (count == 0) {
            return new PageResult<>();
        }
        // 分页查询角色列表
        List<RoleVO> roleVOList = roleMapper.selectRoleVOList(PageUtils.getLimit(), PageUtils.getSize(), condition);
        return new PageResult<>(roleVOList, count);
    }

    @Override
    public void addRole(RoleDTO role) {
        Role existRole = roleMapper.selectOne(new LambdaQueryWrapper<Role>()
                .select(Role::getId)
                .eq(Role::getRoleName, role.getRoleName())
        );
        Assert.isNull(existRole, role.getRoleName() + "角色名已存在");
        // 添加新角色
        Role newRole = Role.builder()
                .roleName(role.getRoleName())
                .roleDesc(role.getRoleDesc())
                .isDisable(role.getIsDisable())
                .build();
        baseMapper.insert(newRole);
        // 添加角色菜单权限
        roleMenuMapper.insertRoleMenu(newRole.getId(), role.getMenuIdList());
    }

    @Override
    public void deleteRole(List<String> roleIdList) {
        Assert.isFalse(roleIdList.contains(ADMIN), "不允许删除管理员角色");
        // 角色是否已分配
        Long count = userRoleMapper.selectCount(new LambdaQueryWrapper<UserRole>().in(UserRole::getRoleId, roleIdList));
        Assert.isFalse(count > 0, "角色已分配");
        // 删除角色
        roleMapper.deleteBatchIds(roleIdList);
        // 批量删除角色关联的菜单权限
        roleMenuMapper.deleteRoleMenu(roleIdList);
        // 删除redis缓存中的菜单权限
        roleIdList.forEach(roleId -> {
            SaSession sessionById = SaSessionCustomUtil.getSessionById("role-" + roleId, false);
            Optional.ofNullable(sessionById).ifPresent(saSession -> saSession.delete("Permission_List"));
        });
    }

    @Override
    public void updateRole(RoleDTO role) {
        Assert.isFalse(role.getId().equals(ADMIN) && role.getIsDisable().equals(TRUE), "不允许禁用管理员");
        // 角色名是否存在,这里预防更改角色的id，即更新不会去更新id。
        Role existRole = roleMapper.selectOne(new LambdaQueryWrapper<Role>().select(Role::getId).eq(Role::getRoleName, role.getRoleName()));
        Assert.isFalse(Objects.nonNull(existRole) && !existRole.getId().equals(role.getId()),
                role.getRoleName() + "角色已存在");
        // 更新角色信息
        Role newRole = Role.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .roleDesc(role.getRoleDesc())
                .isDisable(role.getIsDisable())
                .build();
        roleMapper.updateById(newRole);
        // 先删除角色关联的菜单权限
        roleMenuMapper.deleteRoleMenuByRoleId(newRole.getId());
        // 在添加角色的菜单权限
        roleMenuMapper.insertRoleMenu(newRole.getId(), role.getMenuIdList());
        // 删除Redis中缓存的菜单权限
        SaSession sessionById = SaSessionCustomUtil.getSessionById("role-" + newRole.getId(), false);
        Optional.ofNullable(sessionById).ifPresent(saSession -> saSession.delete("Permission_List"));

    }

    @Override
    public void updateRoleStatus(RoleStatusDTO roleStatus) {
        Assert.isFalse(roleStatus.getId().equals(ADMIN), "不允许禁用管理员角色");
        // 更新角色状态
        Role newRole = Role.builder()
                .id(roleStatus.getId())
                .isDisable(roleStatus.getIsDisable())
                .build();
        roleMapper.updateById(newRole);
    }

    @Override
    public List<Integer> listRoleMenuTree(String roleId) {
        return roleMenuMapper.selectMenuByRoleId(roleId);
    }
}
