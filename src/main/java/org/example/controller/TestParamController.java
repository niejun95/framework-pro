package org.example.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className TestParamController
 * @author niejun
 * @date 2022/6/10
 * @description
 * @version 1.0
 **/
@RestController
@RequestMapping("/testParam")
public class TestParamController {

    public static final Logger log = LogManager.getLogger(TestParamController.class);


    @RequestMapping("/pp")
    public void testParam(HttpServletRequest request, HttpServletResponse response) {
        try {
            int rs = 10 /0;
        } catch (Exception e) {
            log.error("err info", e);
        }
        log.info("我还继续往下执行了哦");
    }
}
