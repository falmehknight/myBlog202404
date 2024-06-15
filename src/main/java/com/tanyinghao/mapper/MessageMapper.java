package com.tanyinghao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.Message;
import com.tanyinghao.model.vo.MessageBackVO;
import com.tanyinghao.model.vo.MessageVO;
import com.tanyinghao.model.vo.PageResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper extends BaseMapper<Message> {


    /**
     *
     * @Author TanYingHao
     * @Description 查看留言列表
     * @Date 17:56 2024/6/15
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.MessageVO>
     **/
    @Select("SELECT id, nickname, avatar, message_content FROM t_message WHERE is_check = 1")
    List<MessageVO> selectMessageVOList();

    /**
     *
     * @Author TanYingHao
     * @Description 查看后台留言列表
     * @Date 17:59 2024/6/15
     * @Param [limit, size, condition]
     * @return java.util.List<com.tanyinghao.model.vo.MessageBackVO>
     **/
    List<MessageBackVO> selectMessageBackVOList(@Param("limit") Long limit, @Param("size") Long size, @Param("condition") ConditionDTO condition);
}
