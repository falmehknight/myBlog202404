package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.entity.Friend;
import com.tanyinghao.model.vo.FriendBackVO;
import com.tanyinghao.model.vo.FriendVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendMapper extends BaseMapper<Friend> {
    /**
     *
     * @Author TanYingHao
     * @Description 查看友链后台列表
     * @Date 18:22 2024/6/15
     * @Param [limit, size, keyword]
     * @return java.util.List<com.tanyinghao.model.vo.FriendBackVO>
     **/
    List<FriendBackVO> selectFriendBackVOList(@Param("limit") Long limit, @Param("size") Long size, @Param("keyword") String keyword);

    /**
     *
     * @Author TanYingHao
     * @Description 查询友链列表
     * @Date 18:26 2024/6/15
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.FriendVO>
     **/
    @Select("SELECT id, color, `name`, avatar, url, introduction FROM t_friend ")
    List<FriendVO> selectFriendVOList();
}
