package com.tanyinghao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.User;
import com.tanyinghao.model.vo.UserBackVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     *
     * @Author TanYingHao
     * @Description 查询后台用户数
     * @Date 21:15 2024/6/10
     * @Param [condition]
     * @return java.lang.Long
     **/
    Long countUser(@Param("condition") ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 查询后台用户列表
     * @Date 21:22 2024/6/10
     * @Param [limit, size, condition]
     * @return java.util.List<com.tanyinghao.model.vo.UserBackVO>
     **/
    List<UserBackVO> listUserBackVO(@Param("limit") Long limit, @Param("size") Long size, @Param("condition") ConditionDTO condition);
}
