package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.dto.AlbumDTO;
import com.tanyinghao.model.entity.Album;
import com.tanyinghao.model.vo.AlbumBackVO;
import com.tanyinghao.model.vo.AlbumVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     *
     * @Author TanYingHao
     * @Description 查询相册信息
     * @Date 14:53 2024/6/15
     * @Param [limit, size, keyword]
     * @return java.util.List<com.tanyinghao.model.vo.AlbumBackVO>
     **/
    List<AlbumBackVO> selectAlbumBackVO(@Param("limit") Long limit, @Param("size") Long size, @Param("keyword") String keyword);

    /**
     *
     * @Author TanYingHao
     * @Description 根据相册id查询相册显示信息
     * @Date 14:58 2024/6/15
     * @Param [albumId]
     * @return com.tanyinghao.model.dto.AlbumDTO
     **/
    @Select("SELECT id,album_name, album_desc, album_cover, `status` FROM t_album WHERE id = #{albumId}")
    AlbumDTO selectAlbumById(@Param("albumId") Integer albumId);

    /**
     *
     * @Author TanYingHao
     * @Description 查看相册列表
     * @Date 15:04 2024/6/15
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.AlbumVO>
     **/
    @Select("SELECT id, album_name, album_desc, album_cover FROM t_album")
    List<AlbumVO> selectAlbumVOList();
}
