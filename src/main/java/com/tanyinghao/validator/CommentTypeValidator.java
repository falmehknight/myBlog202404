package com.tanyinghao.validator;

import com.tanyinghao.comm.annotation.CommentType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName CommentTypeValidator
 * @Description 评论类型校验器
 * @Author 谭颍豪
 * @Date 2024/6/14 14:34
 * @Version 1.0
 **/
public class CommentTypeValidator implements ConstraintValidator<CommentType, Integer> {

    private final Set<Integer> set = new HashSet<>();

    @Override
    public void initialize(CommentType constraintAnnotation) {
        for (int value : constraintAnnotation.values()) {
            set.add(value);
        }
    }

    /**
     *
     * @Author TanYingHao
     * @Description 校验方法
     * @Date 14:39 2024/6/14
     * @Param [value, context] 校验的值, 校验上下文
     * @return boolean
     **/
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return set.contains(value);
    }
}
