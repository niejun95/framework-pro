package org.example.controller.degrade;

import lombok.extern.slf4j.Slf4j;
import org.example.annotation.Degrade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 服务降级
 * @author: ryan
 * @date 2023/5/6 10:17
 * @version: 1.0
 */
@RestController
@RequestMapping("/degrade")
@Slf4j
public class TestDegradeController {

    @RequestMapping
    @Degrade(businessKey = "milestone", sceneKey = "app", methodClass = DegradeHandler.class, methodName = "test")
    public void test() {
        log.info("正常执行");
        int i = 10 / 0;
    }
}
