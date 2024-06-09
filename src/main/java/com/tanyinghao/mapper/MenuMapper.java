package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.MenuDTO;
import com.tanyinghao.model.entity.Menu;
import com.tanyinghao.model.vo.MenuOption;
import com.tanyinghao.model.vo.MenuTree;
import com.tanyinghao.model.vo.MenuVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    
    /**
     *
     * @Author TanYingHao
     * @Description 根据角色id查询对应的权限
     * @Date 16:25 2024/6/7
     * @Param [roleId]
     * @return java.util.List<java.lang.String>
     **/
    @Select("select m.perms from t_menu m JOIN t_role_menu rm ON m.id = rm.menu_id " +
            "where rm.role_id = #{roleId} and m.is_disable = 0")
    List<String> selectPermissionByRoleId(@Param("roleId") String roleId);

    /**
     *
     * @Author TanYingHao
     * @Description 查询所有的菜单列表
     * @Date 23:54 2024/6/8
     * @Param [condition]
     * @return java.util.List<com.tanyinghao.model.vo.MenuVO>
     **/
    List<MenuVO> selectMenuVOList(@Param("condition")ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 查询所有的菜单树
     * @Date 10:52 2024/6/9
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.MenuTree>
     **/
    @Select("select id,parent_id,menu_name as label FROM t_menu WHERE is_disable = 0 ORDER BY order_num")
    List<MenuTree> selectMenuTree();

    /**
     *
     * @Author TanYingHao
     * @Description 查询菜单选项树
     * @Date 10:58 2024/6/9
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.MenuOption>
     **/
    @Select("SELECT id as 'value', parent_id, menu_name as label FROM t_menu WHERE is_disable = 0 " +
            "AND menu_type IN('M','C') ORDER BY order_num")
    List<MenuOption> selectMenuOptions();

    /**
     *
     * @Author TanYingHao
     * @Description 根据id查询菜单信息
     * @Date 15:41 2024/6/9
     * @Param [menuId]
     * @return com.tanyinghao.model.dto.MenuDTO
     **/
    @Select("SELECT id, parent_id, menu_type, menu_name, `path`, icon, component, perms, is_hidden, is_disable," +
            "order_num FROM t_menu WHERE id = #{menuId}")
    MenuDTO selectMenuById(@Param("menuId") Integer menuId);
}
