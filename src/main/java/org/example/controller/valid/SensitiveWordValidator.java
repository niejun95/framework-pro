package org.example.controller.valid;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class SensitiveWordValidator implements ConstraintValidator<CheckText, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String word = "sb";

        if (StrUtil.isEmpty(s)) {
            return true;
        }
        return !s.contains(word);
    }
}
