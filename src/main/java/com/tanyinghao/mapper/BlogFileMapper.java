package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.entity.BlogFile;
import com.tanyinghao.model.vo.FileVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogFileMapper extends BaseMapper<BlogFile> {

    /**
     *
     * @Author TanYingHao
     * @Description 查询文件列表
     * @Date 21:00 2024/6/12
     * @Param [limit, size, filePath] 分页查询的页码，大小，文件路径
     * @return java.util.List<com.tanyinghao.model.vo.FileVO>
     **/
    List<FileVO> selectFileVOList(@Param("limit") Long limit, @Param("size") Long size, @Param("filePath") String filePath);
}
