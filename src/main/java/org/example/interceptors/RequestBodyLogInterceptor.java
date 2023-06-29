package org.example.interceptors;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author ryan
 * @version 1.0
 * @className SelfInterceptor
 * @createTime 2022-10-19  16:00
 * @description 请求报文打印拦截器
 */
public class RequestBodyLogInterceptor implements HandlerInterceptor {
    public static final Logger log = LogManager.getLogger(RequestBodyLogInterceptor.class);

    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();
        log.info("URI：{}", requestURI);
        Map<String, String[]> map = request.getParameterMap();
        log.info("Parameters：\n {}", JSON.toJSONString(map));
        return true;
    }
}
