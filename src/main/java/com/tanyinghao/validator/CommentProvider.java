package com.tanyinghao.validator;

import com.tanyinghao.model.dto.CommentDTO;
import com.tanyinghao.validator.groups.ArticleTalk;
import com.tanyinghao.validator.groups.Link;
import com.tanyinghao.validator.groups.ParentIdNotNull;
import com.tanyinghao.validator.groups.ParentIdNull;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.tanyinghao.comm.enums.CommentTypeEnum.*;

/**
 * @ClassName CommentProvider
 * @Description 评论分组校验器
 * @Author 谭颍豪
 * @Date 2024/6/14 15:31
 * @Version 1.0
 **/
public class CommentProvider implements DefaultGroupSequenceProvider<CommentDTO> {

    @Override
    public List<Class<?>> getValidationGroups(CommentDTO commentDTO) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        defaultGroupSequence.add(CommentDTO.class);
        if (commentDTO != null) {
            if (commentDTO.getCommentType().equals(ARTICLE.getType()) || commentDTO.getCommentType().equals(TALK.getType())) {
                defaultGroupSequence.add(ArticleTalk.class);
            }
            if (commentDTO.getCommentType().equals(LINK.getType())) {
                defaultGroupSequence.add(Link.class);
            }
            if (Objects.isNull(commentDTO.getParentId())) {
                defaultGroupSequence.add(ParentIdNull.class);
            } else {
                defaultGroupSequence.add(ParentIdNotNull.class);
            }
        }
        return defaultGroupSequence;
    }
}
