package org.example.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entities.User;
import org.example.utils.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestRedisController
 * @Author niejun
 * @Date 2022/6/7
 * @Description:
 * @Version 1.0
 **/
@RestController
@RequestMapping("/redis")
public class TestRedisController {

    public static final Logger log = LogManager.getLogger(TestRedisController.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final String DEFAULT_KEY_PREFIX = "";

    @Autowired
    private CacheService cacheService;

    @RequestMapping("/setKey")
    public String setKey() {
        String value = "ryan";
        cacheService.add("name", value);
        log.info("添加字符串到redis");
        return "添加字符串到redis";
    }

    @RequestMapping("/addJsonString")
    public String addJsonString() {
        User user = User.builder().id(123).name("小萌").age(27).build();
        cacheService.add(user.getId(), user);
        log.info("将对象转换成jsonString并存入redis");
        return "将对象转换成jsonString并存入redis";
    }

    @RequestMapping("/addJsonStringExpire")
    public String addJsonStringExpire() {
        ArrayList<User> users = new ArrayList<>();
        User user = User.builder().id(243).name("小萌").age(18).build();
        users.add(user);
        cacheService.add("key", users, 1, TimeUnit.HOURS);
        log.info("将对象集合转换成jsonString，并设置过期时间存入至redis");
        return "将对象集合转换成jsonString，并设置过期时间存入至redis";
    }

    @RequestMapping("/getObject")
    public User getObject() {
        User object = cacheService.getObject(123, User.class);
        System.out.println("object = " + object);
        log.info("获取对象");
        return object;
    }

    @RequestMapping("/getObjects")
    public List<User> getObjects() {
        List<User> users = cacheService.getList("key", User.class);
        log.info("获取对象集合");
        return users;
    }

    @RequestMapping("/addHashCache")
    public String addHashCache() {
        cacheService.addHashCache("hashKey", "key", "value");
        return "添加hash-set";
    }
}
