package com.tanyinghao.service.Impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.comm.utils.BeanCopyUtils;
import com.tanyinghao.mapper.MenuMapper;
import com.tanyinghao.mapper.RoleMenuMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.MenuDTO;
import com.tanyinghao.model.entity.Menu;
import com.tanyinghao.model.entity.RoleMenu;
import com.tanyinghao.model.vo.MenuOption;
import com.tanyinghao.model.vo.MenuTree;
import com.tanyinghao.model.vo.MenuVO;
import com.tanyinghao.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.tanyinghao.comm.constant.CommConstant.PARENT_ID;

/**
 * @ClassName MenuServiceImpl
 * @Description 菜单管理服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/8 23:50
 * @Version 1.0
 **/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<MenuVO> listMenuVO(ConditionDTO condition) {
        // 查询当前菜单列表
        List<MenuVO> menuVOList = menuMapper.selectMenuVOList(condition);
        // 当前菜单id列表
        Set<Integer> menuIdList = menuVOList.stream()
                .map(MenuVO::getId)
                .collect(Collectors.toSet());
        return menuVOList.stream().map(menuVO -> {
            Integer parentId = menuVO.getParentId();
            // parentId不在当前菜单id列表，说明为父级菜单id，根据此id作为递归的开始条件节点
            if (!menuIdList.contains(parentId)) {
                menuIdList.add(parentId);
                return recurMenuList(parentId, menuVOList);
            }
            return new ArrayList<MenuVO>();
        }).collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
    }

    @Override
    public void addMenu(MenuDTO menu) {
        // 查询名称是否存在
        Menu menu1 = menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                .select(Menu::getId)
                .eq(Menu::getMenuName, menu.getMenuName()));
        Assert.isNull(menu1, menu.getMenuName() + "菜单已存在");
        Menu menu2 = BeanCopyUtils.copyBean(menu, Menu.class);
        baseMapper.insert(menu2);
    }

    @Override
    public void deleteMenu(Integer menuId) {
        // 存在子菜单
        Long count = menuMapper.selectCount(new LambdaQueryWrapper<Menu>()
                .eq(Menu::getParentId, menuId));
        Assert.isFalse(count > 0, "存在子菜单");
        // 菜单是否已分配
        Long roleCount = roleMenuMapper.selectCount(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getMenuId, menuId));
        Assert.isFalse(roleCount > 0, "菜单已分配");
        // 删除菜单
        menuMapper.deleteById(menuId);
    }

    @Override
    public void updateMenu(MenuDTO menu) {
        // 该名称是否已存在，即名称一样，id变了，这种情况是无法更新的
        Menu existMenu = menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                .select(Menu::getId)
                .eq(Menu::getMenuName, menu.getMenuName()));
        Assert.isFalse(Objects.nonNull(existMenu) && !existMenu.getId().equals(menu.getId()),
                menu.getMenuName() + "菜单已存在");
        Menu menu1 = BeanCopyUtils.copyBean(menu, Menu.class);
        baseMapper.updateById(menu1);
    }

    @Override
    public List<MenuTree> listMenuTree() {
        List<MenuTree> menuTreeList = menuMapper.selectMenuTree();
        return recurMenuTreeList(PARENT_ID, menuTreeList);
    }

    @Override
    public List<MenuOption> listMenuOption() {
        List<MenuOption> menuOptionList = menuMapper.selectMenuOptions();
        return recurMenuOptionList(PARENT_ID, menuOptionList);
    }

    @Override
    public MenuDTO editMenu(Integer menuId) {
        return menuMapper.selectMenuById(menuId);
    }

    /**
     *
     * @Author TanYingHao
     * @Description 递归生成菜单列表，即MenuVO里面的那个子List
     * @Date 11:03 2024/6/9
     * @Param [parentId, menuVOList]
     * @return java.util.List<com.tanyinghao.model.vo.MenuVO>
     **/
    private List<MenuVO> recurMenuList(Integer parentId, List<MenuVO> menuVOList) {
        return menuVOList.stream()
                .filter(menuVO -> menuVO.getParentId().equals(parentId))
                .peek(menuVO -> menuVO.setChildren(recurMenuList(menuVO.getId(),menuVOList)))
                .collect(Collectors.toList());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 递归生成菜单下拉树
     * @Date 11:03 2024/6/9
     * @Param [parentId, menuTreeList]
     * @return java.util.List<com.tanyinghao.model.vo.MenuTree>
     **/
    private List<MenuTree> recurMenuTreeList(Integer parentId, List<MenuTree> menuTreeList) {
        return menuTreeList.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .peek(menu -> menu.setChildren(recurMenuTreeList(menu.getId(), menuTreeList)))
                .collect(Collectors.toList());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 递归生成菜单选项树
     * @Date 11:04 2024/6/9
     * @Param [parentId, menuOptionList]
     * @return java.util.List<com.tanyinghao.model.vo.MenuOption>
     **/
    private List<MenuOption> recurMenuOptionList(Integer parentId, List<MenuOption> menuOptionList) {
        return menuOptionList.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .peek(menu -> menu.setChildren(recurMenuOptionList(menu.getValue(), menuOptionList)))
                .collect(Collectors.toList());
    }
}
