package com.tanyinghao.service;

import com.tanyinghao.model.vo.BlogBackInfoVO;
import com.tanyinghao.model.vo.BlogInfoVO;

public interface BlogInfoService {

    /**
     *
     * @Author TanYingHao
     * @Description 上传访客信息,记录博客浏览量
     * @Date 18:34 2024/6/15
     * @Param []
     **/
    void report();

    /**
     *
     * @Author TanYingHao
     * @Description 获取博客信息
     * @Date 18:36 2024/6/15
     * @Param []
     * @return com.tanyinghao.model.vo.BlogInfoVO
     **/
    BlogInfoVO getBlogInfo();

    /**
     *
     * @Author TanYingHao
     * @Description 得到博客后台信息
     * @Date 18:37 2024/6/15
     * @Param []
     * @return com.tanyinghao.model.vo.BlogBackInfoVO
     **/
    BlogBackInfoVO getBlogBackInfo();

    /**
     *
     * @Author TanYingHao
     * @Description 查看关于我信息
     * @Date 18:37 2024/6/15
     * @Param []
     * @return java.lang.String
     **/
    String getAbout();
}
