package com.tanyinghao.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tanyinghao.comm.annotation.OptLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.FriendDTO;
import com.tanyinghao.model.vo.FriendBackVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tanyinghao.comm.constant.OptTypeConstant.*;

/**
 * @ClassName FriendController
 * @Description  友链控制器
 * @Author 谭颍豪
 * @Date 2024/6/15 18:11
 * @Version 1.0
 **/
@Api(tags = "后台友链管理模块")
@RestController
@RequestMapping("/admin/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    /**
     *
     * @Author TanYingHao
     * @Description 查询友链后台列表
     * @Date 18:16 2024/6/15
     * @Param [condition]
     * @return com.tanyinghao.comm.result.Result<PageResult<FriendBackVO>>
     **/
    @ApiOperation(value = "查看友链后台列表")
    @SaCheckPermission("web:friend:list")
    @GetMapping("/list")
    public Result<PageResult<FriendBackVO>> listFriendBackVO(ConditionDTO condition) {
        return Result.success(friendService.listFriendBackVO(condition));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 添加友链
     * @Date 18:18 2024/6/15
     * @Param [friend]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = ADD)
    @ApiOperation(value = "添加友链")
    @SaCheckPermission("web:friend:add")
    @PostMapping("/add")
    public Result<?> addFriend(@Validated @RequestBody FriendDTO friend) {
        friendService.addFriend(friend);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 删除友链
     * @Date 18:20 2024/6/15
     * @Param [friendIdList]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = DELETE)
    @ApiOperation(value = "删除友链")
    @SaCheckPermission("web:friend:delete")
    @DeleteMapping("/delete")
    public Result<?> deleteFriend(@RequestBody List<Integer> friendIdList) {
        friendService.removeByIds(friendIdList);
        return Result.success();
    }

   /**
    *
    * @Author TanYingHao
    * @Description 修改友链
    * @Date 18:20 2024/6/15
    * @Param [friend]
    * @return com.tanyinghao.comm.result.Result<?>
    **/
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "修改友链")
    @SaCheckPermission("web:friend:update")
    @PutMapping("/update")
    public Result<?> updateFriend(@Validated @RequestBody FriendDTO friend) {
        friendService.updateFriend(friend);
        return Result.success();
    }

}
