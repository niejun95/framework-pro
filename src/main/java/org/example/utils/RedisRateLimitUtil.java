package org.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controller.TestRedisController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

/**
 * @className: RedisRateLimitUtil
 * @author ryan
 * @CreateTime: 2022-08-16  10:49
 * @description 限流工具类
 * @version 1.0
 */
@Component
public class RedisRateLimitUtil {
    public static final Logger log = LogManager.getLogger(RedisRateLimitUtil.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private DefaultRedisScript<Boolean> script;


    /**
     * 限流检测(单个接口)
     * @param interfaceName 需要限流的接口名
     * @param maxPermits 最大令牌数
     * @param tokensPerSeconds 每秒生成的令牌数
     * @return 是否通过限流 true: 通过
     */
    public boolean acquireToken(String interfaceName, long maxPermits, long tokensPerSeconds) {
        // 错误的参数将不起作用
        if (maxPermits <= 0 || tokensPerSeconds <= 0) {
            log.warn("maxPermits and tokensPerSeconds can not be less than zero...");
            return true;
        }

        // 参数结构: KEYS = [限流的key]   ARGV = [最大令牌数, 每秒生成的令牌数, 本次请求的毫秒数]
        Boolean result = this.redisTemplate.execute(this.script, Collections.singletonList(interfaceName), maxPermits, tokensPerSeconds, System.currentTimeMillis());
        return result != null && result;
    }
}
