package org.example.controller.distributelock;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author : niejun
 * @Description: 操作 redis 中 bigkey
 * @date Date : 2022年07月06日 21:17
 **/
@RestController
@RequestMapping("/bigkey")
public class TestRedisBigKeyController {

    public static final Logger log = LogManager.getLogger(TestRedisBigKeyController.class);

    @Autowired
    private Jedis jedis;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/setBigKey")
    public String setBigKey() {
        User user = User.builder().id(243).name("小萌").age(18).build();
        User ryan = User.builder().id(456).name("ryan").age(27).build();
        //jedis.lpush("key", JSONObject.toJSONString(user), JSONObject.toJSONString(ryan));
        redisTemplate.opsForList().leftPush("key", JSONObject.toJSONString(user));
        return "redis 设置 bigkey list类型成功";

    }

    @RequestMapping("/getBigKey")
    public void getBigKey() {
        List<String> list = jedis.lrange("key", 0, -1);
        log.info("key 中值为：{}", list);
    }

    @RequestMapping("/removeOneElement")
    public void removeOneElement() {
        List<String> key = jedis.lrange("key", 0, -1);
        User ryan = User.builder().id(456).name("ryan").age(27).build();
        jedis.lrem("key", 0, JSONObject.toJSONString(ryan));
    }
}
