package org.example.controller.trans;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: InterceptorConfig
 * @Author: ryan
 * @CreateTime: 2022-10-19  16:11
 * @Description: 拦截器配置
 * @Version: 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestBodyLogInterceptor())
                .addPathPatterns("/**");
    }
}
