package com.tanyinghao.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tanyinghao.comm.annotation.OptLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.TalkDTO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.TalkBackInfoVO;
import com.tanyinghao.model.vo.TalkBackVO;
import com.tanyinghao.service.TalkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.tanyinghao.comm.constant.OptTypeConstant.*;

/**
 * @ClassName TalkController
 * @Description 说说控制器
 * @Author 谭颍豪
 * @Date 2024/6/14 9:51
 * @Version 1.0
 **/
@Api(tags = "后台说说管理模块")
@RestController
@RequestMapping("/admin/talk")
public class TalkController {

    @Autowired
    private TalkService talkService;

    /**
     *
     * @Author TanYingHao
     * @Description 查看后台说说列表
     * @Date 9:57 2024/6/14
     * @Param [condition]
     * @return com.tanyinghao.comm.result.Result<PageResult<TalkBackVO>>
     **/
    @ApiOperation(value = "查看后台说说列表")
    @SaCheckPermission("web:talk:list")
    @GetMapping("/list")
    public Result<PageResult<TalkBackVO>> listTalkBackVO(ConditionDTO condition) {
        return Result.success(talkService.listTalkBackVO(condition));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 上传说说图片
     * @Date 10:02 2024/6/14
     * @Param [file]
     * @return com.tanyinghao.comm.result.Result<java.lang.String>
     **/
    @OptLogger(value = UPLOAD)
    @ApiOperation(value = "上传说说图片")
    @ApiImplicitParam(name = "file", value = "相册封面", required = true, dataType = "MultipartFile")
    @SaCheckPermission("web:talk:upload")
    @PostMapping("/upload")
    public Result<String> uploadTalkCover(@RequestParam("file") MultipartFile file) {
        return Result.success(talkService.uploadTalkCover(file));
    }

   /**
    *
    * @Author TanYingHao
    * @Description 添加说说
    * @Date 10:02 2024/6/14
    * @Param [talk]
    * @return com.tanyinghao.comm.result.Result<?>
    **/
    @OptLogger(value = ADD)
    @ApiOperation(value = "添加说说")
    @SaCheckPermission("web:talk:add")
    @PostMapping("/add")
    public Result<?> addTalk(@Validated @RequestBody TalkDTO talk) {
        talkService.addTalk(talk);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 删除说说
     * @Date 10:05 2024/6/14
     * @Param [talkId]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = DELETE)
    @ApiOperation(value = "删除说说")
    @SaCheckPermission("web:talk:delete")
    @DeleteMapping("/delete/{talkId}")
    public Result<?> deleteTalk(@PathVariable("talkId") Integer talkId) {
        talkService.deleteTalk(talkId);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 修改说说
     * @Date 10:05 2024/6/14
     * @Param [talk]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "修改说说")
    @SaCheckPermission("web:talk:update")
    @PutMapping("/update")
    public Result<?> updateTalk(@Validated @RequestBody TalkDTO talk) {
        talkService.updateTalk(talk);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 编辑说说
     * @Date 10:06 2024/6/14
     * @Param [talkId]
     * @return com.tanyinghao.comm.result.Result<TalkBackInfoVO>
     **/
    @ApiOperation(value = "编辑说说")
    @SaCheckPermission("web:talk:edit")
    @GetMapping("/edit/{talkId}")
    public Result<TalkBackInfoVO> editTalk(@PathVariable("talkId") Integer talkId) {
        return Result.success(talkService.editTalk(talkId));
    }

}
