package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.FriendDTO;
import com.tanyinghao.model.entity.Friend;
import com.tanyinghao.model.vo.FriendBackVO;
import com.tanyinghao.model.vo.FriendVO;
import com.tanyinghao.model.vo.PageResult;

import java.util.List;

public interface FriendService extends IService<Friend> {

    /**
     *
     * @Author TanYingHao
     * @Description 查看友链后台列表
     * @Date 18:18 2024/6/15
     * @Param [condition]
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.FriendBackVO>
     **/
    PageResult<FriendBackVO> listFriendBackVO(ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 添加友链
     * @Date 18:20 2024/6/15
     * @Param [friend]
     **/
    void addFriend(FriendDTO friend);

    /**
     *
     * @Author TanYingHao
     * @Description 修改友链
     * @Date 18:20 2024/6/15
     * @Param [friend]
     **/
    void updateFriend(FriendDTO friend);

    /**
     *
     * @Author TanYingHao
     * @Description 查看友链列表
     * @Date 18:26 2024/6/15
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.FriendVO>
     **/
    List<FriendVO> listFriendVO();
}
