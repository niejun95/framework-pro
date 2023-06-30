package org.example.interceptors;


import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * @author ryan
 * @version 1.0
 * @className SelfInterceptor
 * @createTime 2022-10-19  16:00
 * @description 请求报文打印拦截器
 */
@Slf4j
public class RequestBodyLogInterceptor implements HandlerInterceptor {

    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();
        log.info("Request URI：{}", requestURI);
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        log.info("Request Body：\n {}", body);
        return true;
    }
}
