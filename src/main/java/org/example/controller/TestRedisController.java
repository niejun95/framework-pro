package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.utils.CacheService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestRedisController
 * @Author niejun
 * @Date 2022/6/7
 * @Description:
 * @Version 1.0
 **/
@RestController
@RequestMapping("/redis")
@Slf4j
public class TestRedisController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping("/setKey")
    public String setKey() {
        String value = "ryan";
        cacheService.add("name", value);
        log.info("redis设置成功");
        return "redis设置成功";
    }
}
