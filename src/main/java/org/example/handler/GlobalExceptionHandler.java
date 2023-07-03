package org.example.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.entities.CommonResult;
import org.example.entities.ServiceExceptionEnum;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @author ryan
 * @version 1.0
 * @description 全局异常处理
 * @date 2023/4/27 10:30
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonResult missingServletRequestParameterExceptionHandler(HttpServletRequest request, MissingServletRequestParameterException exception) {
        log.error("[missingServletRequestParameterExceptionHandler]", exception);
        return CommonResult.error(ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getCode(), ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonResult constraintViolationExceptionHandler(HttpServletRequest request, ConstraintViolationException exception) {
        log.error("[constraintViolationExceptionHandler]", exception);
        // 拼接错误
        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : exception.getConstraintViolations()) {
            if (builder.length() > 0) {
                builder.append(";");
            }
            builder.append(constraintViolation.getMessage());
        }
        return CommonResult.error(ServiceExceptionEnum.INVALID_REQUEST_PARAM_ERROR.getCode(), ServiceExceptionEnum.INVALID_REQUEST_PARAM_ERROR.getMessage() + ":" + builder.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public CommonResult bindExceptionHandler(HttpServletRequest request, BindException exception) {
        log.info("=====进入了 BindException =====");
        log.error("[bindExceptionHandler]", exception);
        StringBuilder builder = new StringBuilder();
        for (ObjectError objectError : exception.getAllErrors()) {
            if (builder.length() > 0) {
                builder.append(";");
            }
            builder.append(objectError.getDefaultMessage());
        }
        return CommonResult.error(ServiceExceptionEnum.INVALID_REQUEST_PARAM_ERROR.getCode(), ServiceExceptionEnum.INVALID_REQUEST_PARAM_ERROR.getMessage() + ":" + builder.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException exception) {
        log.info("=====进入了 MethodArgumentNotValidException =====");
        log.error("[methodArgumentNotValidExceptionHandler]", exception);
        StringBuilder builder = new StringBuilder();
        for (ObjectError objectError : exception.getBindingResult().getAllErrors()) {
            if (builder.length() > 0) {
                builder.append(";");
            }
            builder.append(objectError.getDefaultMessage());
        }
        return CommonResult.error(ServiceExceptionEnum.INVALID_REQUEST_PARAM_ERROR.getCode(), ServiceExceptionEnum.INVALID_REQUEST_PARAM_ERROR.getMessage() + ":" + builder.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult exceptionHandler(HttpServletRequest request, Exception exception) {
        log.error("[exceptionHandler]", exception);
        return CommonResult.error(ServiceExceptionEnum.SYS_ERROR.getCode(), ServiceExceptionEnum.SYS_ERROR.getMessage() + ",异常信息为：" + exception.getMessage());

    }
}
