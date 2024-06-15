package com.tanyinghao.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.mapper.PhotoMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.PhotoDTO;
import com.tanyinghao.model.dto.PhotoInfoDTO;
import com.tanyinghao.model.entity.Photo;
import com.tanyinghao.model.vo.AlbumBackVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.PhotoBackVO;
import com.tanyinghao.service.PhotoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName PhotoServiceImpl
 * @Description 照片模块服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/15 13:40
 * @Version 1.0
 **/
@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements PhotoService {

    @Override
    public PageResult<PhotoBackVO> listPhotoBackVO(ConditionDTO condition) {
        return null;
    }

    @Override
    public AlbumBackVO getAlbumInfo(Integer albumId) {
        return null;
    }

    @Override
    public String uploadPhoto(MultipartFile file) {
        return null;
    }

    @Override
    public void addPhoto(PhotoDTO photo) {

    }

    @Override
    public void updatePhoto(PhotoInfoDTO photoInfo) {

    }

    @Override
    public void deletePhoto(List<Integer> photoIdList) {

    }

    @Override
    public void movePhoto(PhotoDTO photo) {

    }
}
