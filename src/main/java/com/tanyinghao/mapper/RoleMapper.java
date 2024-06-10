package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.Role;
import com.tanyinghao.model.vo.RoleVO;
import com.tanyinghao.model.vo.UserRoleVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     *
     * @Author TanYingHao
     * @Description 查询后台角色数量
     * @Date 11:04 2024/6/7
     * @Param [condition] 条件
     * @return java.lang.Long
     **/
    Long selectCountRoleVO(@Param("condition")ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 查询角色列表
     * @Date 11:12 2024/6/7
     * @Param [limit, size, condition]
     * @return java.util.List<com.tanyinghao.model.vo.RoleVO>
     **/
    List<RoleVO> selectRoleVOList(@Param("limit") Long limit, @Param("size") Long size, @Param("condition") ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 根据用户id查询用户角色
     * @Date 16:34 2024/6/7
     * @Param [userId]
     * @return java.util.List<java.lang.String>
     **/
    @Select("select r.id from t_role r JOIN t_user_role tur on r.id = tur.role_id " +
            "where tur.user_id = #{userId} and r.is_disable = 0")
    List<String> selectRoleListByUserId(@Param("userId") Object userId);

    /**
     *
     * @Author TanYingHao
     * @Description 查询角色列表
     * @Date 21:29 2024/6/10
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.UserRoleVO>
     **/
    @Select("select id, role_name from t_role")
    List<UserRoleVO> selectUserRoleList();
}
