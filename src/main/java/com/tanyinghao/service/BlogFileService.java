package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.dto.FolderDTO;
import com.tanyinghao.model.entity.BlogFile;
import com.tanyinghao.model.vo.FileVO;
import com.tanyinghao.model.vo.PageResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogFileService extends IService<BlogFile> {

    /**
     *
     * @Author TanYingHao
     * @Description 查看文件列表
     * @Date 20:48 2024/6/12
     * @Param [condition] 查询条件
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.FileVO>
     **/
    PageResult<FileVO> listFileVOList(ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 上传文件
     * @Date 20:49 2024/6/12
     * @Param [file, path] 文件， 路径
     **/
    void uploadFile(MultipartFile file, String path);

    /**
     *
     * @Author TanYingHao
     * @Description 创建目录
     * @Date 20:51 2024/6/12
     * @Param [folder] 目录的属性
     **/
    void createFolder(FolderDTO folder);

    /**
     *
     * @Author TanYingHao
     * @Description 删除文件
     * @Date 20:52 2024/6/12
     * @Param [fileIdList] 待删除文件的id集合
     **/
    void deleteFile(List<Integer> fileIdList);
}
