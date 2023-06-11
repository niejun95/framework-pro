package org.example.controller.distributelock;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author niejun
 * @description 操作 redis 中 bigkey
 * @date 2022年07月06日 21:17
 **/
@RestController
@RequestMapping("/bigkey")
public class TestRedisBigKeyController {

    public static final Logger log = LogManager.getLogger(TestRedisBigKeyController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/setBigKey")
    public String setBigKey() {
        User user = User.builder().id(243).name("小萌").age(18).build();
        User ryan = User.builder().id(456).name("ryan").age(27).build();
        redisTemplate.opsForList().leftPushAll("key1", user, ryan);
        return "redis 设置 bigkey list类型成功";

    }

    @RequestMapping("/getBigKey/{key}")
    public void getBigKey(@PathVariable String key) {
        List list = redisTemplate.opsForList().range(key, 0, -1);
        log.info("list 的长度为：{}", list.size());
        log.info("内容为：{}", list);
        for (Object o : list) {
            User user = JSON.parseObject(JSON.toJSONString(o), User.class);
            System.out.println(user);
        }
    }

    @RequestMapping("/removeOneElement")
    public void removeOneElement() {
        String s1 = "123";
        String s2 = "345";
        String s3 = "123";
        redisTemplate.opsForList().leftPushAll("key2",s1, s2, s3);

        redisTemplate.opsForList().remove("key2", 1, "345");

    }
}
