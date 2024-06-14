package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.TalkDTO;
import com.tanyinghao.model.entity.Talk;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.TalkBackInfoVO;
import com.tanyinghao.model.vo.TalkBackVO;
import com.tanyinghao.model.vo.TalkVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TalkService extends IService<Talk> {

    /**
     *
     * @Author TanYingHao
     * @Description 查看后台说说列表
     * @Date 10:01 2024/6/14
     * @Param [condition]
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.TalkBackVO>
     **/
    PageResult<TalkBackVO> listTalkBackVO(ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 上传说说图片
     * @Date 10:02 2024/6/14
     * @Param [file]
     * @return java.lang.String
     **/
    String uploadTalkCover(MultipartFile file);

    /**
     *
     * @Author TanYingHao
     * @Description 添加说说
     * @Date 10:04 2024/6/14
     * @Param [talk]
     **/
    void addTalk(TalkDTO talk);

    /**
     *
     * @Author TanYingHao
     * @Description 删除说说
     * @Date 10:05 2024/6/14
     * @Param [talkId]
     **/
    void deleteTalk(Integer talkId);

    /**
     *
     * @Author TanYingHao
     * @Description 修改说说
     * @Date 10:05 2024/6/14
     * @Param [talk]
     **/
    void updateTalk(TalkDTO talk);

    /**
     *
     * @Author TanYingHao
     * @Description 编辑说说
     * @Date 10:07 2024/6/14
     * @Param [talkId]
     * @return com.tanyinghao.model.vo.TalkBackInfoVO
     **/
    TalkBackInfoVO editTalk(Integer talkId);

    /**
     *
     * @Author TanYingHao
     * @Description 查看首页说说
     * @Date 11:23 2024/6/14
     * @Param []
     * @return java.util.List<java.lang.String>
     **/
    List<String> listTalkHome();

    /**
     *
     * @Author TanYingHao
     * @Description 查看说说列表
     * @Date 11:25 2024/6/14
     * @Param []
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.TalkVO>
     **/
    PageResult<TalkVO> listTalkVO();

    /**
     *
     * @Author TanYingHao
     * @Description 根据id查询说说
     * @Date 11:26 2024/6/14
     * @Param [talkId]
     * @return com.tanyinghao.model.vo.TalkVO
     **/
    TalkVO getTalkById(Integer talkId);
}
