package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.AlbumDTO;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.Album;
import com.tanyinghao.model.vo.AlbumBackVO;
import com.tanyinghao.model.vo.AlbumVO;
import com.tanyinghao.model.vo.PageResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AlbumService extends IService<Album> {

    /**
     *
     * @Author TanYingHao
     * @Description 查看后台相册列表
     * @Date 14:43 2024/6/15
     * @Param [condition]
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.AlbumBackVO>
     **/
    PageResult<AlbumBackVO> listAlbumBackVO(ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 上传相册封面
     * @Date 14:44 2024/6/15
     * @Param [file]
     * @return java.lang.String
     **/
    String uploadAlbumCover(MultipartFile file);

    /**
     *
     * @Author TanYingHao
     * @Description 添加相册
     * @Date 14:46 2024/6/15
     * @Param [album]
     **/
    void addAlbum(AlbumDTO album);

    /**
     *
     * @Author TanYingHao
     * @Description 删除相册
     * @Date 14:47 2024/6/15
     * @Param [albumId]
     **/
    void deleteAlbum(Integer albumId);

    /**
     *
     * @Author TanYingHao
     * @Description 修改相册
     * @Date 14:48 2024/6/15
     * @Param [album]
     **/
    void updateAlbum(AlbumDTO album);

    /**
     *
     * @Author TanYingHao
     * @Description 编辑相册
     * @Date 14:48 2024/6/15
     * @Param [albumId]
     * @return com.tanyinghao.model.dto.AlbumDTO
     **/
    AlbumDTO editAlbum(Integer albumId);

    /**
     *
     * @Author TanYingHao
     * @Description 查看相册VO
     * @Date 15:03 2024/6/15
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.AlbumVO>
     **/
    List<AlbumVO> listAlbumVO();
}
