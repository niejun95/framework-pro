package org.example.preventAop;

import java.lang.annotation.*;

/**
 * @className: Prevent
 * @author ryan
 * @CreateTime: 2022-10-10  11:12
 * @description 接口防刷 注解
 * @version 1.0
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Prevent {
    /**
     * 限制的时间值（秒）
     * @return
     */
    String value() default "60";

    /**
     * 提示
     * @return
     */
    String message() default "";

    PreventStrategy strategy() default PreventStrategy.DEFAULT;
}
