package org.example.config;

import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName TraceIdInterceptor
 * @Author niejun
 * @Date 2022/6/15
 * @Description:
 * @Version 1.0
 **/
@Component
public class TraceIdInterceptor extends HandlerInterceptorAdapter {
    private static final String TRACE_ID = "TRACE_ID";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Date date = new Date();
        String timePrefix = sdf.format(date);
        MDC.put(TRACE_ID, timePrefix + UUID.randomUUID().toString().substring(0,3));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        MDC.clear();
    }

}
