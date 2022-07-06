package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author : niejun
 * @Description: TODO
 * @date Date : 2022年07月06日 21:54
 **/
@Configuration
public class JedisPoolUtils {

    @Autowired
    RedisConfig redisConfig;


    @Bean
    public Jedis getJedis() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(redisConfig.getMaxTotal());
        config.setMaxIdle(redisConfig.getMaxIdle());
        config.setMinIdle(redisConfig.getMinIdle());
        JedisPool jedisPool = new JedisPool(config, redisConfig.getHost(), redisConfig.getPort());
        Jedis resource = jedisPool.getResource();
        return resource;
    }
}
