package com.tanyinghao.comm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName CommentTypeEnum
 * @Description 评论类型枚举
 * @Author 谭颍豪
 * @Date 2024/6/14 12:03
 * @Version 1.0
 **/
@Getter
@AllArgsConstructor
public enum CommentTypeEnum {
    /**
     * 文章评论
     */
    ARTICLE(1, "文章", "article/"),
    /**
     * 友链评论
     */
    LINK(2, "友链", "friend"),
    /**
     * 说说评论
     */
    TALK(3, "说说", "talk/");

    /**
     * 状态
     */
    private final Integer type;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 路径
     */
    private final String path;

    /**
     * 获取评论路径
     *
     * @param type 类型
     * @return {@link String}
     */
    public static String getCommentPath(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value.getPath();
            }
        }
        return null;
    }
}
