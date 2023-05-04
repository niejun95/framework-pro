package org.example.controller.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Constraint(validatedBy = {SensitiveWordValidator.class})
public @interface CheckText {

    String message() default "不要输入敏感词";

    // 默认函数 不能缺少
    Class<?>[] groups() default {};

    // 默认函数 不能缺少
    Class<? extends Payload>[] payload() default {};
}
