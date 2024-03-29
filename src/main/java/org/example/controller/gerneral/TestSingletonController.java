package org.example.controller.gerneral;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.SingletonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className TestSingletonController
 * @author niejun
 * @createTime 2022-07-18  15:34
 * @description 测试 spring 中单例
 * @version 1.0
 */
@RestController
@RequestMapping("/testSingleton")
public class TestSingletonController {
    public static final Logger log = LogManager.getLogger(TestSingletonController.class);

    public static ConfigurableApplicationContext context;

    @Autowired
    private SingletonEntity singletonEntity1;

    @Autowired
    private SingletonEntity singletonEntity2;

    @RequestMapping("/getFirst")
    public SingletonEntity getFirstSingleton() {
        SingletonEntity singletonEntity = context.getBean("singletonEntity", SingletonEntity.class);
        singletonEntity.setAge(17);
        singletonEntity.setName("ryan");
        log.info("getFirstSingleton,{}", singletonEntity);
        return singletonEntity;
    }

    @RequestMapping("/getSecond")
    public SingletonEntity getSecondSingleton() {
        SingletonEntity singletonEntity = context.getBean("singletonEntity", SingletonEntity.class);
        log.info("getSecondSingleton,{}", singletonEntity);
        return singletonEntity;
    }

}
