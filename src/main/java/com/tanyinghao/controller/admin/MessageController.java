package com.tanyinghao.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tanyinghao.comm.annotation.OptLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.CheckDTO;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.vo.MessageBackVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tanyinghao.comm.constant.OptTypeConstant.DELETE;
import static com.tanyinghao.comm.constant.OptTypeConstant.UPDATE;

/**
 * @ClassName MessageController
 * @Description 留言控制器
 * @Author 谭颍豪
 * @Date 2024/6/15 15:50
 * @Version 1.0
 **/
@Api(tags = "后台留言管理模块")
@RestController
@RequestMapping("/admin/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     *
     * @Author TanYingHao
     * @Description 查看后台留言列表
     * @Date 15:53 2024/6/15
     * @Param [condition]
     * @return com.tanyinghao.comm.result.Result<PageResult<MessageBackVO>>
     **/
    @ApiOperation(value = "查看后台留言列表")
    @SaCheckPermission("news:message:list")
    @GetMapping("/list")
    public Result<PageResult<MessageBackVO>> listMessageBackVO(ConditionDTO condition) {
        return Result.success(messageService.listMessageBackVO(condition));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 删除留言
     * @Date 15:54 2024/6/15
     * @Param [messageIdList]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = DELETE)
    @ApiOperation(value = "删除留言")
    @SaCheckPermission("news:message:delete")
    @DeleteMapping("/delete")
    public Result<?> deleteMessage(@RequestBody List<Integer> messageIdList) {
        messageService.removeByIds(messageIdList);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 审核留言
     * @Date 15:55 2024/6/15
     * @Param [check]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "审核留言")
    @SaCheckPermission("news:message:pass")
    @PutMapping("/pass")
    public Result<?> updateMessageCheck(@Validated @RequestBody CheckDTO check) {
        messageService.updateMessageCheck(check);
        return Result.success();
    }
}
