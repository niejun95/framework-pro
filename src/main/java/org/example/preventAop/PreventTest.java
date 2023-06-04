package org.example.preventAop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: PreventTest
 * @author ryan
 * @CreateTime: 2022-10-10  13:46
 * @description 防刷测试
 * @version 1.0
 */
@RestController
@RequestMapping("/prevent")
public class PreventTest {
    public static final Logger log = LogManager.getLogger(PreventAop.class);

    @RequestMapping("/testPrevent/{id}")
    @Prevent(value = "30", message = "有毛病")
    public void test(@PathVariable String id) {
        log.info("开始测试防刷");
        System.out.println(id);
    }
}
