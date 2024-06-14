package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.entity.Talk;
import com.tanyinghao.model.vo.TalkBackInfoVO;
import com.tanyinghao.model.vo.TalkBackVO;
import com.tanyinghao.model.vo.TalkVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalkMapper extends BaseMapper<Talk> {

    /**
     *
     * @Author TanYingHao
     * @Description 分页查询说说列表
     * @Date 10:34 2024/6/14
     * @Param [limit, size, status]
     * @return java.util.List<com.tanyinghao.model.vo.TalkBackVO>
     **/
    List<TalkBackVO> selectTalkBackVO(@Param("limit") Long limit, @Param("size") Long size, @Param("status") Integer status);

    /**
     *
     * @Author TanYingHao
     * @Description 根据id查询说说后台信息
     * @Date 10:47 2024/6/14
     * @Param [talkId]
     * @return com.tanyinghao.model.vo.TalkBackInfoVO
     **/
    @Select("SELECT id, talk_content, images, is_top, `status` FROM t_talk WHERE id = #{talkId}")
    TalkBackInfoVO selectTalkBackById(@Param("talkId") Integer talkId);

    /**
     *
     * @Author TanYingHao
     * @Description 根据id查询说说信息
     * @Date 11:42 2024/6/14
     * @Param [talkId]
     * @return com.tanyinghao.model.vo.TalkVO
     **/
    TalkVO selectTalkById(@Param("talkId") Integer talkId);

    /**
     *
     * @Author TanYingHao
     * @Description 分页查询说说
     * @Date 11:53 2024/6/14
     * @Param [limit, size]
     * @return java.util.List<com.tanyinghao.model.vo.TalkVO>
     **/
    List<TalkVO> selectTalkList(@Param("limit") Long limit, @Param("size") Long size);
}
