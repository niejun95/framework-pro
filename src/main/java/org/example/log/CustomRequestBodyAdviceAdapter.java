package org.example.log;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

@ControllerAdvice
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

    @Autowired
    LoggingService loggingService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Override
    public boolean supports(@NotNull MethodParameter methodParameter, @NotNull Type type, @NotNull Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @NotNull
    @Override
    public Object afterBodyRead(@NotNull Object body, @NotNull HttpInputMessage inputMessage, @NotNull MethodParameter parameter, @NotNull Type targetType,
                                @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        loggingService.logRequest(httpServletRequest, body);
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }
}
