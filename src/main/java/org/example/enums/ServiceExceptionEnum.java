package org.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ryan
 * @version 1.0
 * @description 异常枚举
 * @date 2023/4/27 10:25
 */
@Getter
@AllArgsConstructor
public enum ServiceExceptionEnum {
    SUCCESS(0, "成功"),
    SYS_ERROR(2001001000, "服务端发生异常"),

    MISSING_REQUEST_PARAM_ERROR(2001001001, "参数丢失"),
    INVALID_REQUEST_PARAM_ERROR(2001001002, "请求参数不合法"),
    USER_NOT_FOUND(1001002000, "用户不存在");

    private final int code;
    private final String message;

}
