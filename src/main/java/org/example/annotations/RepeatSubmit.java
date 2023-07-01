package org.example.annotations;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {

    /**
     * 间隔事件（ms） 小于此事件视为重复提交
     */
    int interval() default 5000;


    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

    /**
     * 提示消息
     */
    String message() default "不允许重复提交，请稍候再试";

}
