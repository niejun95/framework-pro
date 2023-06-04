package org.example.controller.idempotent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : niejun
 * @description token 幂等测试 controller 类
 * @date Date : 2022年08月07日 15:26
 **/
@RestController
public class TokenController {

    public static final Logger log = LogManager.getLogger(TokenController.class);

    @Autowired
    private TokenUtilService tokenUtilService;

    /**
     * 获取 token 接口
     * @return token 串
     */
    @GetMapping("/token")
    public String getToken() {
        // 获取用户信息（这里使用模拟数据）
        // 注：这里存储该内容只是举例，其作用为辅助验证，使其验证逻辑更加安全，如这里存储用户信息，其目的为：
        // 1、使用 token 验证 redis 中是否存在对应的 key
        // 2、使用用户信息验证 redis 中的 value 的值 是否匹配
        String userInfo = "mydlq";
        // 获取 token 字符串，并返回
        return tokenUtilService.generateToken(userInfo);
    }

    @PostMapping("/test")
    public String test(@RequestHeader(value = "token") String token) {
        // 获取用户信息（这里使用模拟数据）
        String userInfo = "mydlq";
        // 根据 token 和用户相关的信息到 redis 验证是否存在对应的信息
        boolean result = tokenUtilService.validToken(token, userInfo);
        // 根据验证结果响应不同的信息
        return result ? "正常调用" : "重复调用";
    }
}
