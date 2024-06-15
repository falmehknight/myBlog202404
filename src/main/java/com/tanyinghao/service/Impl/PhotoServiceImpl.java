package com.tanyinghao.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.comm.utils.BeanCopyUtils;
import com.tanyinghao.comm.utils.FileUtils;
import com.tanyinghao.comm.utils.PageUtils;
import com.tanyinghao.mapper.AlbumMapper;
import com.tanyinghao.mapper.BlogFileMapper;
import com.tanyinghao.mapper.PhotoMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.PhotoDTO;
import com.tanyinghao.model.dto.PhotoInfoDTO;
import com.tanyinghao.model.entity.BlogFile;
import com.tanyinghao.model.entity.Photo;
import com.tanyinghao.model.vo.AlbumBackVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.model.vo.PhotoBackVO;
import com.tanyinghao.service.PhotoService;
import com.tanyinghao.strategy.context.UploadStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.tanyinghao.comm.constant.CommConstant.FALSE;
import static com.tanyinghao.comm.enums.FilePathEnum.PHOTO;

/**
 * @ClassName PhotoServiceImpl
 * @Description 照片模块服务实现类
 * @Author 谭颍豪
 * @Date 2024/6/15 13:40
 * @Version 1.0
 **/
@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements PhotoService {

    @Autowired
    private PhotoMapper photoMapper;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Autowired
    private BlogFileMapper blogFileMapper;

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public PageResult<PhotoBackVO> listPhotoBackVO(ConditionDTO condition) {
        // 查询照片数量
        Long count = photoMapper.selectCount(new LambdaQueryWrapper<Photo>()
                .eq(Objects.nonNull(condition.getAlbumId()), Photo::getAlbumId, condition.getAlbumId()));
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询照片列表
        List<PhotoBackVO> photoList = photoMapper.selectPhotoBackVOList(PageUtils.getLimit(),
                PageUtils.getSize(), condition.getAlbumId());
        return new PageResult<>(photoList, count);
    }

    @Override
    public AlbumBackVO getAlbumInfo(Integer albumId) {
        AlbumBackVO albumBackVO = albumMapper.selectAlbumInfoById(albumId);
        if (Objects.isNull(albumBackVO)) {
            return null;
        }
        Long count = photoMapper.selectCount(new LambdaQueryWrapper<Photo>().eq(Photo::getAlbumId, albumId));
        albumBackVO.setPhotoCount(count);
        return albumBackVO;
    }

    @Override
    public String uploadPhoto(MultipartFile file) {
        // 上传文件
        String url = uploadStrategyContext.executeUploadStrategy(file, PHOTO.getPath());
        try {
            // 获取文件md5值
            String md5 = FileUtils.getMd5(file.getInputStream());
            // 获取文件扩展名
            String extName = FileUtils.getExtension(file);
            BlogFile existFile = blogFileMapper.selectOne(new LambdaQueryWrapper<BlogFile>()
                    .select(BlogFile::getId)
                    .eq(BlogFile::getFileName, md5)
                    .eq(BlogFile::getFilePath, PHOTO.getFilePath()));
            if (Objects.isNull(existFile)) {
                // 保存文件信息
                BlogFile newFile = BlogFile.builder()
                        .fileUrl(url)
                        .fileName(md5)
                        .filePath(PHOTO.getFilePath())
                        .extendName(extName)
                        .fileSize((int) file.getSize())
                        .isDir(FALSE)
                        .build();
                blogFileMapper.insert(newFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public void addPhoto(PhotoDTO photo) {
        List<Photo> photoList = photo.getPhotoUrlList().stream()
                .map(url -> Photo.builder()
                        .albumId(photo.getAlbumId())
                        .photoName(IdWorker.getIdStr())
                        .photoUrl(url)
                        .build())
                .collect(Collectors.toList());
        this.saveBatch(photoList);
    }

    @Override
    public void updatePhoto(PhotoInfoDTO photoInfo) {
        Photo photo = BeanCopyUtils.copyBean(photoInfo, Photo.class);
        baseMapper.updateById(photo);
    }

    @Override
    public void deletePhoto(List<Integer> photoIdList) {
        baseMapper.deleteBatchIds(photoIdList);
    }

    @Override
    public void movePhoto(PhotoDTO photo) {
        List<Photo> photoList = photo.getPhotoIdList().stream()
                .map(photoId -> Photo.builder()
                        .id(photoId)
                        .albumId(photo.getAlbumId())
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(photoList);
    }
}
