package org.example.config;

import org.example.interceptors.RequestBodyLogInterceptor;
import org.example.interceptors.TraceIdInterceptor;
import org.example.log.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author niejun
 * @version 1.0
 * @className 拦截器配置
 * @date 2022/6/15
 * @description
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceIdInterceptor());
        //registry.addInterceptor(new RequestBodyLogInterceptor())
        //        .addPathPatterns("/**");
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
    }

}
