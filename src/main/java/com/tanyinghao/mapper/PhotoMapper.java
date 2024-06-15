package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.entity.Photo;
import com.tanyinghao.model.vo.PhotoBackVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoMapper extends BaseMapper<Photo> {

    /**
     *
     * @Author TanYingHao
     * @Description 分页查询后台照片列表
     * @Date 14:20 2024/6/15
     * @Param [limit, size, albumId]
     * @return java.util.List<com.tanyinghao.model.vo.PhotoBackVO>
     **/
    List<PhotoBackVO> selectPhotoBackVOList(@Param("limit") Long limit, @Param("size") Long size, @Param("albumId") Integer albumId);
}
