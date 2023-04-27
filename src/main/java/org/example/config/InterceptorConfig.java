package org.example.config;

import org.example.interceptors.RequestBodyLogInterceptor;
import org.example.interceptors.TraceIdInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName 拦截器配置
 * @Author niejun
 * @Date 2022/6/15
 * @Description:
 * @Version 1.0
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceIdInterceptor());
        registry.addInterceptor(new RequestBodyLogInterceptor())
                .addPathPatterns("/**");
    }

}
