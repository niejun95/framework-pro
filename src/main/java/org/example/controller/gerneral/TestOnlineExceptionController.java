package org.example.controller.gerneral;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entities.Account;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className TestOnlineExceptionController
 * @author niejun
 * @date 2022/6/21
 * @description
 * @version 1.0
 **/
@RestController
@RequestMapping("/exception")
public class TestOnlineExceptionController {
    public static final Logger log = LogManager.getLogger(TestOnlineExceptionController.class);


    @RequestMapping("/cpuBoom")
    public void oom() {
        log.info("测试CPU boom");
        while (true) {
            Account account = new Account();
        }
    }

    @RequestMapping("/stackoverflow")
    public void sof() {
        log.info("测试 stack over flow");
        while (true) {
            doNothing();
        }
    }

    public void doNothing() {

    }
}
