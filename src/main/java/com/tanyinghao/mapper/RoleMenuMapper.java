package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.entity.RoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     *
     * @Author TanYingHao
     * @Description 添加角色菜单
     * @Date 11:44 2024/6/7
     * @Param [id, menuIdList]
     * @return void
     **/
    void insertRoleMenu(@Param("roleId") String id, List<Integer> menuIdList);
    
    /**
     *
     * @Author TanYingHao
     * @Description 批量删除角色菜单
     * @Date 14:47 2024/6/7
     * @Param [roleIdList]
     * @return void
     **/
    void deleteRoleMenu(List<String> roleIdList);

    /**
     *
     * @Author TanYingHao
     * @Description 根据角色id删除角色菜单
     * @Date 15:23 2024/6/7
     * @Param [id]
     * @return void
     **/
    @Delete("delete from t_role_menu where role_id = #{roleId}")
    void deleteRoleMenuByRoleId(@Param("roleId") String id);

    /**
     *
     * @Author TanYingHao
     * @Description 根据角色id查询菜单树
     * @Date 15:37 2024/6/7
     * @Param [id]
     * @return java.util.List<java.lang.Integer>
     **/
    @Select("select menu_id from t_role_menu where role_id = #{roleId}")
    List<Integer> selectMenuByRoleId(@Param("roleId") String id);

}
