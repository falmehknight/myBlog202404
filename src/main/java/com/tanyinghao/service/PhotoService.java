package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.PhotoDTO;
import com.tanyinghao.model.dto.PhotoInfoDTO;
import com.tanyinghao.model.entity.Photo;
import com.tanyinghao.model.vo.AlbumBackVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.PhotoBackVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface PhotoService extends IService<Photo> {

    /**
     *
     * @Author TanYingHao
     * @Description 查看后台照片列表
     * @Date 13:49 2024/6/15
     * @Param [condition]
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.PhotoBackVO>
     **/
    PageResult<PhotoBackVO> listPhotoBackVO(ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 查看照片相册信息
     * @Date 13:51 2024/6/15
     * @Param [albumId]
     * @return com.tanyinghao.model.vo.AlbumBackVO
     **/
    AlbumBackVO getAlbumInfo(Integer albumId);

    /**
     *
     * @Author TanYingHao
     * @Description 上传照片
     * @Date 13:53 2024/6/15
     * @Param [file]
     * @return java.lang.String
     **/
    String uploadPhoto(MultipartFile file);

    /**
     *
     * @Author TanYingHao
     * @Description 添加图片
     * @Date 13:54 2024/6/15
     * @Param [photo]
     **/
    void addPhoto(PhotoDTO photo);

    /**
     *
     * @Author TanYingHao
     * @Description 更新图片信息
     * @Date 13:55 2024/6/15
     * @Param [photoInfo]
     **/
    void updatePhoto(PhotoInfoDTO photoInfo);

    /**
     *
     * @Author TanYingHao
     * @Description 删除图片
     * @Date 13:56 2024/6/15
     * @Param [photoIdList]
     **/
    void deletePhoto(List<Integer> photoIdList);

    /**
     *
     * @Author TanYingHao
     * @Description 移动图片
     * @Date 13:57 2024/6/15
     * @Param [photo]
     **/
    void movePhoto(PhotoDTO photo);

    /**
     *
     * @Author TanYingHao
     * @Description 查看照片列表
     * @Date 14:34 2024/6/15
     * @Param [condition]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    Map<String, Object> listPhotoVO(ConditionDTO condition);
}
