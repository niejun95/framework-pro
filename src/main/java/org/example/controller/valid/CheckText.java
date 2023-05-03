package org.example.controller.valid;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Constraint(validatedBy = {SensitiveWordValidator.class})
public @interface CheckText {

    String message() default "不要输入敏感词";

}
