package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.Role;
import com.tanyinghao.model.vo.RoleVO;
import org.apache.ibatis.annotations.Param;
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

}
