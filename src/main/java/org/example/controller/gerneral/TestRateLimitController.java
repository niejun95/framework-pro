package org.example.controller.gerneral;

import org.example.utils.RedisRateLimitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className TestRateLimit
 * @author ryan
 * @createTime 2022-08-16  16:11
 * @description 测试 限流
 * @version 1.0
 */
@RestController
@RequestMapping("/testRateLimit")
public class TestRateLimitController {

    @Autowired
    private RedisRateLimitUtil redisRateLimitUtil;

    @RequestMapping("/test")
    public void test() {
        boolean simple = redisRateLimitUtil.acquireToken("simple", 10, 10);
        if (simple == true) {
            System.out.println("成功");
        }
    }
}
