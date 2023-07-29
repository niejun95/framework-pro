package org.example.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.example.interceptors.MybatisMetaInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(MybatisConfiguration configuration) {
                // 开启驼峰命名映射
                configuration.setMapUnderscoreToCamelCase(true);
                MybatisMetaInterceptor mybatisMetaInterceptor = new MybatisMetaInterceptor();
                Properties properties = new Properties();
                properties.setProperty("param1", "javaconfig-value1");
                properties.setProperty("param2", "javaconfig-value2");
                mybatisMetaInterceptor.setProperties(properties);
                configuration.addInterceptor(mybatisMetaInterceptor);
            }
        };
    }
}
