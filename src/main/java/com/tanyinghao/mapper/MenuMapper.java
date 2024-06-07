package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.entity.Menu;
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
}
