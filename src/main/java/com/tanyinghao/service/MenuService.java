package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.MenuDTO;
import com.tanyinghao.model.entity.Menu;
import com.tanyinghao.model.vo.MenuOption;
import com.tanyinghao.model.vo.MenuTree;
import com.tanyinghao.model.vo.MenuVO;

import java.util.List;

public interface MenuService extends IService<Menu> {

    /**
     *
     * @Author TanYingHao
     * @Description 查看菜单列表
     * @Date 23:46 2024/6/8
     * @Param [condition]
     * @return java.util.List<com.tanyinghao.model.entity.Menu>
     **/
    List<MenuVO> listMenuVO(ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 添加菜单
     * @Date 23:47 2024/6/8
     * @Param [menu]
     * @return void
     **/
    void addMenu(MenuDTO menu);

    /**
     *
     * @Author TanYingHao
     * @Description 删除某个菜单
     * @Date 23:47 2024/6/8
     * @Param [menuId]
     * @return void
     **/
    void deleteMenu(Integer menuId);

    /**
     *
     * @Author TanYingHao
     * @Description 更新菜单
     * @Date 23:48 2024/6/8
     * @Param [menu]
     * @return void
     **/
    void updateMenu(MenuDTO menu);

    /**
     *
     * @Author TanYingHao
     * @Description 获取菜单树
     * @Date 23:48 2024/6/8
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.MenuTree>
     **/
    List<MenuTree> listMenuTree();

    /**
     *
     * @Author TanYingHao
     * @Description 获取菜单选项树
     * @Date 23:49 2024/6/8
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.MenuOption>
     **/
    List<MenuOption> listMenuOption();
}
