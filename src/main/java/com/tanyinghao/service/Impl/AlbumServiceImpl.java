package com.tanyinghao.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.mapper.AlbumMapper;
import com.tanyinghao.model.dto.AlbumDTO;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.Album;
import com.tanyinghao.model.vo.AlbumBackVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.service.AlbumService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName AlbumServiceImpl
 * @Description 相册模块服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/15 14:38
 * @Version 1.0
 **/
@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {
    @Override
    public PageResult<AlbumBackVO> listAlbumBackVO(ConditionDTO condition) {
        return null;
    }

    @Override
    public String uploadAlbumCover(MultipartFile file) {
        return null;
    }

    @Override
    public void addAlbum(AlbumDTO album) {

    }

    @Override
    public void deleteAlbum(Integer albumId) {

    }

    @Override
    public void updateAlbum(AlbumDTO album) {

    }

    @Override
    public AlbumDTO editAlbum(Integer albumId) {
        return null;
    }
}
