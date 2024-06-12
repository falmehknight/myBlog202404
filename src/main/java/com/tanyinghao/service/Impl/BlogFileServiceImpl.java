package com.tanyinghao.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.mapper.BlogFileMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.FolderDTO;
import com.tanyinghao.model.entity.BlogFile;
import com.tanyinghao.model.vo.FileVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.service.BlogFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName BlogFileServiceImpl
 * @Description 文件业务接口实现类
 * @Author 谭颍豪
 * @Date 2024/6/12 20:44
 * @Version 1.0
 **/
@Service
public class BlogFileServiceImpl extends ServiceImpl<BlogFileMapper, BlogFile> implements BlogFileService {

    @Override
    public PageResult<FileVO> listFileVOList(ConditionDTO condition) {
        return null;
    }

    @Override
    public void uploadFile(MultipartFile file, String path) {

    }

    @Override
    public void createFolder(FolderDTO folder) {

    }

    @Override
    public void deleteFile(List<Integer> fileIdList) {

    }
}
