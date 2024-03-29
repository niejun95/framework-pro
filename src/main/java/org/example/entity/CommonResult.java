package org.example.entity;

import cn.hutool.core.lang.Assert;
import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.example.constants.CommonConstants;
import org.slf4j.MDC;

import java.io.Serializable;

/**
 * @description 通用返回结果
 * @author ryan
 * @date 2023/4/27 10:13
 * @version 1.0
 */
@Getter
@Setter
public class CommonResult<T> implements Serializable {
    public static Integer CODE_SUCCESS = HttpStatus.HTTP_OK;

    private Integer code;

    private String message;

    private String traceId;

    private T data;

    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.getCode(), result.getMessage());
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        Assert.isTrue(!CODE_SUCCESS.equals(code), "code 必须是错误的！");
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.message = message;
        result.traceId = MDC.get(CommonConstants.TRACE_ID);
        return result;
    }

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = CODE_SUCCESS;
        result.data = data;
        result.message = "";
        result.traceId = MDC.get(CommonConstants.TRACE_ID);
        return result;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return CODE_SUCCESS.equals(code);
    }

    @JsonIgnore
    public boolean isError() {
        return !isSuccess();
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", message=" + message +
                ", data=" + data + "}";
    }
}
