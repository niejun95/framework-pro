package org.example.controller.distributelock;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName DistributionLockConfig
 * @Author niejun
 * @Date 2022/6/30
 * @Description:
 * @Version 1.0
 **/
@Configuration
public class DistributionLockConfig {

    @Bean
    public Redisson redisson() {
        // 此为单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://116.62.186.162:6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }

}
