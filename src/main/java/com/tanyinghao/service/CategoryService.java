package com.tanyinghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanyinghao.model.dto.CategoryDTO;
import com.tanyinghao.model.dto.ConditionDTO;
import com.tanyinghao.model.entity.Category;
import com.tanyinghao.model.vo.*;

import java.util.List;

public interface CategoryService extends IService<Category> {
    /**
     *
     * @Author TanYingHao
     * @Description 查看后台分类列表
     * @Date 0:01 2024/6/13
     * @Param [condition]
     * @return com.tanyinghao.model.vo.PageResult<com.tanyinghao.model.vo.CategoryBackVO>
     **/
    PageResult<CategoryBackVO> listCategoryBackVO(ConditionDTO condition);

    /**
     *
     * @Author TanYingHao
     * @Description 添加分类
     * @Date 0:03 2024/6/13
     * @Param [category] 分类的属性
     **/
    void addCategory(CategoryDTO category);

    /**
     *
     * @Author TanYingHao
     * @Description 删除分类
     * @Date 0:04 2024/6/13
     * @Param [categoryIdList] 分类id集合
     **/
    void deleteCategory(List<Integer> categoryIdList);

    /**
     *
     * @Author TanYingHao
     * @Description 更新分类
     * @Date 0:05 2024/6/13
     * @Param [category] 分类属性
     **/
    void updateCategory(CategoryDTO category);

    /**
     *
     * @Author TanYingHao
     * @Description 查看分类选项
     * @Date 0:06 2024/6/13
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.CategoryOptionVO>
     **/
    List<CategoryOptionVO> listCategoryOption();

    /**
     *
     * @Author TanYingHao
     * @Description 查看分类列表
     * @Date 0:13 2024/6/13
     * @Param []
     * @return java.util.List<com.tanyinghao.model.vo.CategoryVO>
     **/
    List<CategoryVO> listCategoryVO();

    /**
     *
     * @Author TanYingHao
     * @Description 查看分类下的文章
     * @Date 0:14 2024/6/13
     * @Param [condition]
     * @return com.tanyinghao.model.vo.ArticleConditionList
     **/
    ArticleConditionList listArticleCategory(ConditionDTO condition);
}
