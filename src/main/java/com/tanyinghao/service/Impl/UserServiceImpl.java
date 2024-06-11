package com.tanyinghao.service.Impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.comm.utils.PageUtils;
import com.tanyinghao.comm.utils.SecurityUtils;
import com.tanyinghao.mapper.MenuMapper;
import com.tanyinghao.mapper.RoleMapper;
import com.tanyinghao.mapper.UserMapper;
import com.tanyinghao.mapper.UserRoleMapper;
import com.tanyinghao.model.dto.*;
import com.tanyinghao.model.entity.User;
import com.tanyinghao.model.entity.UserRole;
import com.tanyinghao.model.vo.*;
import com.tanyinghao.service.RedisService;
import com.tanyinghao.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

import static com.tanyinghao.comm.constant.CommConstant.*;
import static com.tanyinghao.comm.constant.RedisConstant.*;

/**
 * @ClassName UserServiceImpl
 * @Description 用户服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/9 15:47
 * @Version 1.0
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public UserBackInfoVO getUserBackInfo() {
        Integer userId = StpUtil.getLoginIdAsInt();
        // 查询用户头像
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getAvatar)
                .eq(User::getId, userId));
        // 查询用户角色集合
        List<String> roleList = StpUtil.getRoleList();
        // 查询用户权限集合
        List<String> permissionList = StpUtil.getPermissionList().stream()
                .filter(string -> !string.isEmpty())
                .distinct()
                .collect(Collectors.toList());

        return UserBackInfoVO.builder()
                .id(userId)
                .avatar(user.getAvatar())
                .roleList(roleList)
                .permissionList(permissionList)
                .build();
    }

    @Override
    public List<RouterVO> getUserMenu() {
        // 查询用户菜单
        List<UserMenuVO> userMenuVOList = menuMapper.selectMenuByUserId(StpUtil.getLoginIdAsInt());
        // 递归生成路由，parentId为0
        return recurRoutes(PARENT_ID, userMenuVOList);
    }



    @Override
    public PageResult<UserBackVO> listUserBackVO(ConditionDTO condition) {
        // 查询后台用户数
        Long count = userMapper.countUser(condition);
        if (count == 0) {
            return new PageResult<>();
        }
        List<UserBackVO> userBackVOList = userMapper.listUserBackVO(PageUtils.getLimit(), PageUtils.getSize(), condition);
        return new PageResult<>(userBackVOList, count);
    }

    @Override
    public List<UserRoleVO> listUserRoleVO() {
        // 查看角色列表
        return roleMapper.selectUserRoleList();
    }

    @Override
    public void updateUser(UserRoleDTO userRole) {
        // 更新用户信息
        User newUser = User.builder()
                .id(userRole.getId())
                .nickname(userRole.getNickname())
                .build();
        baseMapper.updateById(newUser);
        // 删除用户角色
        userRoleMapper.delete(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, userRole.getId()));
        // 重新添加用户角色
        userRoleMapper.insertUserRole(userRole.getId(), userRole.getRoleIdList());
        // 删除redis缓存中的角色
        SaSession sessionByLoginId = StpUtil.getSessionByLoginId(userRole.getId(), false);
        Optional.ofNullable(sessionByLoginId).ifPresent(saSession -> saSession.delete("Role_List"));

    }

    @Override
    public void updateUserStatus(DisableDTO disable) {
        // 更新用户状态
        User newUser = User.builder()
                .id(disable.getId())
                .isDisable(disable.getIsDisable())
                .build();
        userMapper.updateById(newUser);
        if (disable.getIsDisable().equals(TRUE)) {
            // 先下线
            StpUtil.logout(disable.getId());
            // 在封禁账号
            StpUtil.disable(disable.getId(), 86400);
        } else {
            // 解除封禁
            StpUtil.untieDisable(disable.getId());
        }
    }

    @Override
    public PageResult<OnlineVO> listOnlineUser(ConditionDTO condition) {
        // 查询所有会话token
        List<String> tokenList = StpUtil.searchTokenSessionId("", 0, -1, false);
        List<OnlineVO> onlineVOList = tokenList.stream()
                .map(token -> {
                    SaSession sessionBySessionId = StpUtil.getSessionBySessionId(token);
                    return (OnlineVO) sessionBySessionId.get(ONLINE_USER);
                })
                .filter(onlineVO -> StringUtils.isEmpty(condition.getKeyword()) || onlineVO.getNickname().contains(condition.getKeyword()))
                .sorted(Comparator.comparing(OnlineVO::getLoginTime).reversed())
                .collect(Collectors.toList());
        // 分页
        int fromIndex = PageUtils.getLimit().intValue();
        int size = PageUtils.getSize().intValue();
        int toIndex = onlineVOList.size() - fromIndex > size ? fromIndex + size : onlineVOList.size();
        List<OnlineVO> userOnlineList = onlineVOList.subList(fromIndex, toIndex);
        return new PageResult<>(userOnlineList, (long) onlineVOList.size());
    }

    @Override
    public void kickOutUser(String token) {
        StpUtil.logoutByTokenValue(token);
    }

    @Override
    public void updateAdminPassword(PasswordDTO password) {
        Integer userId = StpUtil.getLoginIdAsInt();
        // 检查旧密码是否正确
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, userId));
        Assert.notNull(user, "用户不存在");
        Assert.isTrue(SecurityUtils.checkPw(user.getPassword(), password.getOldPassword()), "旧密码错误!");
        // 正确则修改密码
        String newPassword = SecurityUtils.sha256Encrypt(password.getNewPassword());
        user.setPassword(newPassword);
        userMapper.updateById(user);
    }

    @Override
    public UserInfoVO getUserInfo() {
        Integer userId = StpUtil.getLoginIdAsInt();
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getAvatar, User::getNickname, User::getUsername, User::getEmail, User::getWebSite,
                        User::getIntro, User::getLoginType)
                .eq(User::getId, userId));
        Set<Object> articleLikeSet = redisService.getSet(USER_ARTICLE_LIKE + userId);
        Set<Object> commentLikeSet = redisService.getSet(USER_COMMENT_LIKE + userId);
        Set<Object> talkLikeSet = redisService.getSet(USER_TALK_LIKE + userId);
        return UserInfoVO.builder()
                .avatar(user.getAvatar())
                .nickname(user.getNickname())
                .username(user.getUsername())
                .email(user.getEmail())
                .webSite(user.getWebSite())
                .intro(user.getIntro())
                .loginType(user.getLoginType())
                .articleLikeSet(articleLikeSet)
                .commentLikeSet(commentLikeSet)
                .talkLikeSet(talkLikeSet)
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserEmail(EmailDTO email) {
        verifyCode(email.getEmail(),email.getCode());
        User user = User.builder()
                .id(StpUtil.getLoginIdAsInt())
                .email(email.getEmail())
                .build();
        userMapper.updateById(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateUserAvatar(MultipartFile file) {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserInfo(UserInfoDTO userInfo) {
        User newUser = User.builder()
                .nickname(userInfo.getNickname())
                .intro(userInfo.getIntro())
                .webSite(userInfo.getWebSite())
                .build();
        userMapper.updateById(newUser);
    }

    @Override
    public void updatePassword(UserDTO user) {
        verifyCode(user.getUsername(), user.getCode());
        User existUser = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getUsername)
                .eq(User::getUsername, user.getUsername()));
        Assert.notNull(existUser, "邮箱未注册");
        // 根据用户名修改密码
        userMapper.update(new User(), new LambdaUpdateWrapper<User>()
                .set(User::getPassword, SecurityUtils.sha256Encrypt(user.getPassword()))
                .eq(User::getUsername, user.getUsername()));
    }

    private void verifyCode(String username, String code) {
        String sysCode = redisService.getObject(CODE_KEY + username);
        Assert.notBlank(sysCode, "验证码未发送或者已经过期");
        Assert.isTrue(sysCode.equals(code), "验证码错误，请重新输入");
    }

    /**
     *
     * @Author TanYingHao
     * @Description 递归生成路由
     * @Date 10:30 2024/6/10
     * @Param [parentId, userMenuVOList]
     * @return java.util.List<com.tanyinghao.model.vo.RouterVO>
     **/
    private List<RouterVO> recurRoutes(Integer parentId, List<UserMenuVO> userMenuVOList) {
        List<RouterVO> list = new ArrayList<>();
        Optional.ofNullable(userMenuVOList).ifPresent(menus -> menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .forEach(menu ->{
                    RouterVO routerVO = new RouterVO();
                    routerVO.setName(menu.getMenuName());
                    routerVO.setPath(getRouterPath(menu));
                    routerVO.setComponent(getComponent(menu));
                    routerVO.setMeta(MetaVO.builder()
                            .title(menu.getMenuName())
                            .icon(menu.getIcon())
                            .hidden(menu.getIsHidden().equals(TRUE))
                            .build());
                    if (menu.getMenuType().equals(TYPE_DIR)) {
                        List<RouterVO> children = recurRoutes(menu.getId(), userMenuVOList);
                        if (CollectionUtil.isNotEmpty(children)) {
                            routerVO.setAlwaysShow(true);
                            routerVO.setRedirect("noRedirect");
                        }
                        routerVO.setChildren(children);
                    } else if (isMenuFrame(menu)) {
                        routerVO.setMeta(null);
                        List<RouterVO> childrenList = new ArrayList<>();
                        RouterVO children = new RouterVO();
                        children.setName(menu.getMenuName());
                        children.setPath(menu.getPath());
                        children.setComponent(menu.getComponent());
                        children.setMeta(MetaVO.builder()
                                .title(menu.getMenuName())
                                .icon(menu.getIcon())
                                .hidden(menu.getIsHidden().equals(TRUE))
                                .build());
                        childrenList.add(children);
                        routerVO.setChildren(childrenList);
                    }
                    list.add(routerVO);
                }));
        return list;
    }

    /**
     *
     * @Author TanYingHao
     * @Description 获取组件信息
     * @Date 10:43 2024/6/10
     * @Param [menu]
     * @return java.lang.String
     **/
    private String getComponent(UserMenuVO menu) {
        String component = LAYOUT;
        if (StringUtils.isNoneEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = PARENT_VIEW;
        }
        return component;
    }

    /**
     *
     * @Author TanYingHao
     * @Description 是否为parent_view组件
     * @Date 10:48 2024/6/10
     * @Param [menu]
     * @return boolean
     **/
    private boolean isParentView(UserMenuVO menu) {
        return !menu.getParentId().equals(PARENT_ID) && TYPE_DIR.equals(menu.getMenuType());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 是否为菜单内部跳转
     * @Date 10:46 2024/6/10
     * @Param [menu]
     * @return boolean
     **/
    private boolean isMenuFrame(UserMenuVO menu) {
        return menu.getParentId().equals(PARENT_ID) && TYPE_MENU.equals(menu.getMenuType());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 获取路由地址
     * @Date 10:35 2024/6/10
     * @Param [menu]
     * @return java.lang.String
     **/
    private String getRouterPath(UserMenuVO menu) {
        String path = menu.getPath();
        // 一级目录
        if (menu.getParentId().equals(PARENT_ID) && TYPE_DIR.equals(menu.getMenuType())) {
            path = "/" + menu.getPath();
        } else if (menu.getParentId().equals(PARENT_ID) && TYPE_MENU.equals(menu.getMenuType())) { // 一级菜单
            path = "/";
        }
        return path;
    }


}
