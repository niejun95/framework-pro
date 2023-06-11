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
 * @className SelfInterceptor
 * @author ryan
 * @createTime 2022-10-19  16:00
 * @description 请求报文打印拦截器
 * @version 1.0
 */
public class RequestBodyLogInterceptor implements HandlerInterceptor {
    public static final Logger log = LogManager.getLogger(RequestBodyLogInterceptor.class);

    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();
        log.info("当前访问的URI：{}", requestURI);
        Map<String, String[]> map = request.getParameterMap();
        log.info("请求参数信息：\n {}", JSON.toJSONString(map));
        return true;
    }
}
