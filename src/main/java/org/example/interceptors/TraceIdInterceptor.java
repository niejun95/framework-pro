package org.example.interceptors;

import org.example.constants.CommonConstants;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @className TraceIdInterceptor
 * @author ryan
 * @date 2022/6/15
 * @description 日志全局跟踪号
 * @version 1.0
 **/
public class TraceIdInterceptor implements HandlerInterceptor {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler)
            throws Exception {
        Date date = new Date();
        String timePrefix = SDF.format(date);
        MDC.put(CommonConstants.TRACE_ID, timePrefix + UUID.randomUUID().toString().substring(0, 3));
        return true;
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, @Nullable Exception ex) throws Exception {
        MDC.clear();
    }

}
