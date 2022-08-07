package org.example.controller.idempotent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author : niejun
 * @Description: Token 工具类
 * @date Date : 2022年08月07日 15:09
 **/
@Service
public class TokenUtilService {
    public static final Logger log = LogManager.getLogger(TokenUtilService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 存入 redis 的 token 键的前缀
     */
    private static final String IDEMPOTENT_TOKEN_PREFIX = "idempotent_token:";

    /**
     * 创建 token 存入 redis， 并返回该 token
     * @param value 用于辅助验证的 value 值
     * @return 生成的 token 串
     */
    public String generateToken(String value) {
        // 实例化生成ID工具类
        String token = UUID.randomUUID().toString();
        // 设置存入 redis 的 key
        String key = IDEMPOTENT_TOKEN_PREFIX + token;
        // 存储 token 到 redis 且设置过期时间为 5分钟
        redisTemplate.opsForValue().set(key, value, 5 , TimeUnit.MINUTES);
        // 返回 token
        return token;
    }

    /**
     * 验证 token 正确性
     * @param token token 字符串
     * @param value 存储在 redis 中的辅助验证信息
     * @return 验证结果
     */
    public boolean validToken(String token, String value) {
        // 设置 lua 脚本，其中keys[1] 是key, keys[2]是 value
        String script = "if redis.call('get', KEYS[1]) == KEYS[2] then return redis.call('del', KEYS[1]) else return " +
                "0 end";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        // 根据 key 前缀拼接 key
        String key = IDEMPOTENT_TOKEN_PREFIX + token;
        // 执行 lua 脚本
        Long result = redisTemplate.execute(redisScript, Arrays.asList(key, value));
        // 根据 返回结果判断是否成功？成功匹配并删除 redis 键值对，若结果不为空或0，则验证通过
        if (result != null && result != 0L) {
            log.info("验证 token={}, key={},value={} 成功", token, key, value);
            return true;
        }
        log.info("验证 token={}, key={},value={} 失败", token, key, value);
        return false;

    }
}
