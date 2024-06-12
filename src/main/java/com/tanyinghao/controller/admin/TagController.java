package com.tanyinghao.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tanyinghao.comm.annotation.OptLogger;
import com.tanyinghao.comm.result.Result;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.TagDTO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.TagBackVO;
import com.tanyinghao.model.vo.TagOptionVO;
import com.tanyinghao.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tanyinghao.comm.constant.OptTypeConstant.*;

/**
 * @ClassName TagController
 * @Description 标签控制器
 * @Author 谭颍豪
 * @Date 2024/6/12 22:12
 * @Version 1.0
 **/
@Api(tags = "标签管理模块")
@RestController
@RequestMapping("/admin/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     *
     * @Author TanYingHao
     * @Description 查看后台标签列表
     * @Date 23:07 2024/6/12
     * @Param [condition]
     * @return com.tanyinghao.comm.result.Result<PageResult<TagBackVO>>
     **/
    @ApiOperation(value = "查看后台标签列表")
    @SaCheckPermission("blog:tag:list")
    @GetMapping("/list")
    public Result<PageResult<TagBackVO>> listTagBackVO(ConditionDTO condition) {
        return Result.success(tagService.listTagBackVO(condition));
    }

    /**
     *
     * @Author TanYingHao
     * @Description 添加标签
     * @Date 23:09 2024/6/12
     * @Param [tag]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = ADD)
    @ApiOperation(value = "添加标签")
    @SaCheckPermission("blog:tag:add")
    @PostMapping("/add")
    public Result<?> addTag(@Validated @RequestBody TagDTO tag) {
        tagService.addTag(tag);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 删除tag
     * @Date 23:11 2024/6/12
     * @Param [tagIdList]
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = DELETE)
    @ApiOperation(value = "删除标签")
    @SaCheckPermission("blog:tag:delete")
    @DeleteMapping("/delete")
    public Result<?> deleteTag(@RequestBody List<Integer> tagIdList) {
        tagService.deleteTag(tagIdList);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 修改标签
     * @Date 23:13 2024/6/12
     * @Param [tag] 标签信息
     * @return com.tanyinghao.comm.result.Result<?>
     **/
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "修改标签")
    @SaCheckPermission("blog:tag:update")
    @PutMapping("/update")
    public Result<?> updateTag(@Validated @RequestBody TagDTO tag) {
        tagService.updateTag(tag);
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 查看标签列表
     * @Date 23:16 2024/6/12
     * @Param []
     * @return com.tanyinghao.comm.result.Result<java.util.List<com.tanyinghao.model.vo.TagOptionVO>>
     **/
    @ApiOperation(value = "查看标签选项")
    @GetMapping("/option")
    public Result<List<TagOptionVO>> listTagOption() {
        return Result.success(tagService.listTagOption());
    }
}
