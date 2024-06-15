package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.entity.Album;
import com.tanyinghao.model.vo.AlbumBackVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumMapper extends BaseMapper<Album> {

    /**
     *
     * @Author TanYingHao
     * @Description 根据id查询相册的信息
     * @Date 14:28 2024/6/15
     * @Param [albumId]
     * @return com.tanyinghao.model.vo.AlbumBackVO
     **/
    @Select("SELECT id, album_name, album_desc, album_cover, status FROM t_album WHERE id = #{albumId}")
    AlbumBackVO selectAlbumInfoById(@Param("albumId") Integer albumId);
}
