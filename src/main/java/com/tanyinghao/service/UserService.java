package com.tanyinghao.service;

import cn.hutool.db.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.DisableDTO;
import com.tanyinghao.model.dto.PasswordDTO;
import com.tanyinghao.model.dto.UserRoleDTO;
import com.tanyinghao.model.entity.User;
import com.tanyinghao.model.vo.*;

import java.util.List;

public interface UserService extends IService<User> {
    /**
     *
     * @Author TanYingHao
     * @Description 获取后台登录用户信息
     * @Date 15:49 2024/6/9
     * @Param []
     * @return com.tanyinghao.model.vo.UserBackInfoVO
     **/
    UserBackInfoVO getUserBackInfo();

    /**
     *
     * @Author TanYingHao
     * @Description 获取登录用户菜单
     * @Date 15:51 2024/6/9
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.RouterVO>
     **/
    List<RouterVO> getUserMenu();

    /**
     *
     * @Author TanYingHao
     * @Description 查看后台用户列表
     * @Date 15:53 2024/6/9
     * @Param [condition]
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.UserBackVO>
     **/
    PageResult<UserBackVO> listUserBackVO(ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 查看用户角色选项
     * @Date 15:53 2024/6/9
     * @Param []
     * @return com.tanyinghao.model.vo.UserRoleVO
     **/
    List<UserRoleVO> listUserRoleVO();

    /**
     *
     * @Author TanYingHao
     * @Description 修改用户
     * @Date 15:54 2024/6/9
     * @Param [userRole]
     **/
    void updateUser(UserRoleDTO userRole);

    /**
     *
     * @Author TanYingHao
     * @Description 修改用户状态
     * @Date 15:55 2024/6/9
     * @Param [disable]
     **/
    void updateUserStatus(DisableDTO disable);

    /**
     *
     * @Author TanYingHao
     * @Description 查看在线用户
     * @Date 15:56 2024/6/9
     * @Param [condition]
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.OnlineVO>
     **/
    PageResult<OnlineVO> listOnlineUser(ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 下线用户
     * @Date 15:56 2024/6/9
     * @Param [token]
     **/
    void kickOutUser(String token);

    /**
     *
     * @Author TanYingHao
     * @Description 修改管理员密码
     * @Date 15:57 2024/6/9
     * @Param [password]
     **/
    void updateAdminPassword(PasswordDTO password);
}
