package com.tanyinghao.controller.user;

import com.tanyinghao.comm.annotation.AccessLimit;
import com.tanyinghao.comm.annotation.VisitLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.MessageDTO;
import com.tanyinghao.model.vo.MessageVO;
import com.tanyinghao.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName MessageController
 * @Description 留言控制器
 * @Author 谭颍豪
 * @Date 2024/6/15 15:50
 * @Version 1.0
 **/
@Api(tags = "前台留言模块")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     *
     * @Author TanYingHao
     * @Description 查看留言列表
     * @Date 15:56 2024/6/15
     * @Param []
     * @return com.tanyinghao.comm.result.Result<java.util.List<MessageVO>>
     **/
    @VisitLogger(value = "留言")
    @ApiOperation(value = "查看留言列表")
    @GetMapping("/list")
    public Result<List<MessageVO>> listMessageVO() {
        return Result.success(messageService.listMessageVO());
    }


    /**
     *
     * @Author TanYingHao
     * @Description 添加留言
     * @Date 15:57 2024/6/15
     * @Param [message]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @AccessLimit(seconds = 60, maxCount = 3)
    @ApiOperation(value = "添加留言")
    @PostMapping("/add")
    public Result<?> addMessage(@Validated @RequestBody MessageDTO message) {
        messageService.addMessage(message);
        return Result.success();
    }
}
