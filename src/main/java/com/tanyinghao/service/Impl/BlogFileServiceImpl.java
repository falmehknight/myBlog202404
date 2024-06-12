package com.tanyinghao.service.Impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanyinghao.comm.exception.ServiceException;
import com.tanyinghao.comm.utils.FileUtils;
import com.tanyinghao.comm.utils.PageUtils;
import com.tanyinghao.mapper.BlogFileMapper;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.FolderDTO;
import com.tanyinghao.model.entity.BlogFile;
import com.tanyinghao.model.vo.FileVO;
import com.tanyinghao.model.vo.PageResult;
import com.tanyinghao.service.BlogFileService;
import com.tanyinghao.strategy.context.UploadStrategyContext;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.tanyinghao.comm.constant.CommConstant.FALSE;
import static com.tanyinghao.comm.constant.CommConstant.TRUE;

/**
 * @ClassName BlogFileServiceImpl
 * @Description 文件业务接口实现类
 * @Author 谭颍豪
 * @Date 2024/6/12 20:44
 * @Version 1.0
 **/
@Service
public class BlogFileServiceImpl extends ServiceImpl<BlogFileMapper, BlogFile> implements BlogFileService {

    @Value("${upload.local.path}")
    private String localPath;
    @Autowired
    private BlogFileMapper blogFileMapper;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Override
    public PageResult<FileVO> listFileVOList(ConditionDTO condition) {
        // 查询文件数量
        Long count = blogFileMapper.selectCount(new LambdaQueryWrapper<BlogFile>()
                .eq(BlogFile::getFilePath, condition.getFilePath()));
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询文件列表
        List<FileVO> fileVOList = blogFileMapper.selectFileVOList(PageUtils.getLimit(), PageUtils.getSize(),
                condition.getFilePath());
        return new PageResult<>(fileVOList, count);
    }

    @Override
    public void uploadFile(MultipartFile file, String path) {
        try {
            String uploadPath = "/".equals(path) ? path : path + "/";
            // 获取文件的md5值和扩展名
            String md5 = FileUtils.getMd5(file.getInputStream());
            String extension = FileUtils.getExtension(file);
            BlogFile existFile = blogFileMapper.selectOne(new LambdaQueryWrapper<BlogFile>()
                    .select(BlogFile::getId)
                    .eq(BlogFile::getFileName, md5)
                    .eq(BlogFile::getFilePath, path));
            Assert.isNull(existFile, "文件已存在");
            // 上传文件
            String url = uploadStrategyContext.executeUploadStrategy(file, uploadPath);
            // 保存文件信息
            BlogFile newFile = BlogFile.builder()
                    .fileUrl(url)
                    .fileName(md5)
                    .filePath(path)
                    .extendName(extension)
                    .fileSize((int) file.getSize())
                    .isDir(FALSE)
                    .build();
            blogFileMapper.insert(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createFolder(FolderDTO folder) {
        String fileName = folder.getFileName();
        String filePath = folder.getFilePath();
        // 判断目录是否存在
        BlogFile blogFile = blogFileMapper.selectOne(new LambdaQueryWrapper<BlogFile>()
                .select(BlogFile::getId)
                .eq(BlogFile::getFileName, fileName)
                .eq(BlogFile::getFilePath, filePath));
        Assert.isNull(blogFile, "目录已存在");
        // 创建目录
        File directory = new File(localPath + filePath + "/" + fileName);
        if (FileUtils.mkdir(directory)) {
            BlogFile newBlogFile = BlogFile.builder()
                    .fileName(fileName)
                    .filePath(filePath)
                    .isDir(TRUE)
                    .build();
            blogFileMapper.insert(newBlogFile);
        } else {
            throw new ServiceException("创建目录失败");
        }


    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteFile(List<Integer> fileIdList) {
        List<BlogFile> blogFiles = blogFileMapper.selectList(new LambdaQueryWrapper<BlogFile>()
                .select(BlogFile::getFileName, BlogFile::getFilePath, BlogFile::getFileName, BlogFile::getIsDir)
                .in(BlogFile::getId, fileIdList));
        // 删除数据库中的文件信息
        blogFileMapper.deleteBatchIds(fileIdList);
        blogFiles.forEach(blogFile -> {
            File file;
            String fileName = localPath + blogFile.getFilePath() + "/" + blogFile.getFileName();
            if (blogFile.getIsDir().equals(TRUE)) {
                // 删除数据库剩下的子文件
                String filePath = blogFile.getFilePath() + blogFile.getFileName();
                blogFileMapper.delete(new LambdaQueryWrapper<BlogFile>().eq(BlogFile::getFilePath, filePath));
                // 删除目录
                file = new File(fileName);
                if (file.exists()) {
                    FileUtils.deleteFile(file);
                }
            } else {
                file = new File(fileName + "." + blogFile.getExtendName());
                if (file.exists()) {
                    file.delete();
                }
            }
        });

    }
}
