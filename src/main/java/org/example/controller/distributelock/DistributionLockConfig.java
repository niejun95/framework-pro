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
        /**
         * 看门狗机制的续命时间设置：
         * 1、加锁时设置了过期时间，那么 Redisson 不会给你开启看门狗的机制
         *      -1或者未设置 从 config 中读取
         * 2、config 中也可以配置看门狗超时续命时间
         * 3、加锁和 config 都没有设置 默认时间为30s
         *
         * 到了 lockWatchdogTimeout 的1/3时候进行检查，没执行完重新设置过期时间为 lockWatchdogTimeout
         */
        config.setLockWatchdogTimeout(10000L);
        return (Redisson) Redisson.create(config);
    }

}
