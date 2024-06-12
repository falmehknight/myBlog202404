package com.tanyinghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanyinghao.model.entity.Category;
import com.tanyinghao.model.vo.CategoryBackVO;
import com.tanyinghao.model.vo.CategoryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     *
     * @Author TanYingHao
     * @Description 分页查询后台分类列表
     * @Date 0:08 2024/6/13
     * @Param [limit, size, keyword] 页码，大小，关键字
     * @return java.util.List<com.tanyinghao.model.vo.CategoryBackVO>
     **/
    List<CategoryBackVO> selectCategoryBackVO(@Param("limit") Long limit, @Param("size") Long size, @Param("keyword") String keyword);

    /**
     *
     * @Author TanYingHao
     * @Description 查询categoryVO
     * @Date 0:14 2024/6/13
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.CategoryVO>
     **/
    List<CategoryVO> selectCategoryVO();
}
