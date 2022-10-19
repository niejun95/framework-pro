package org.example.controller.trans;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName: SelfInterceptor
 * @Author: ryan
 * @CreateTime: 2022-10-19  16:00
 * @Description: 自定义拦截器
 * @Version: 1.0
 */
public class SelfInterceptor implements HandlerInterceptor {
    public static final Logger log = LogManager.getLogger(SelfInterceptor.class);

     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
         String requestURI = request.getRequestURI();
         log.info("当前访问的URI：{}", requestURI);
         Map<String, String[]> map = request.getParameterMap();
         log.info("请求参数信息：\n {}", JSON.toJSONString(map));
         if ("/db/userQ/ryan208".equals(requestURI)) {
             return false;
         }
         return true;
    }
}
