package org.example;

import org.example.controller.gerneral.TestSingletonController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author niejun
 * @description 启动类
 * @date 2022年06月06日 22:42
 **/
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class DemoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        TestSingletonController.context = context;
    }
}
