package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @className: CORSConfiguration
 * @author ryan
 * @CreateTime: 2022-09-09  08:49
 * @description CORS
 * @version 1.0
 */
@Configuration
public class CORSConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST","PUT", "DELETE", "HEAD", "OPTIONS")
                .allowCredentials(true)// 允许携带cookie
                .maxAge(3600)// 设置有效期
                .allowedHeaders("*");
    }
}
