package org.example;

import org.example.controller.TestSingletonController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author : niejun
 * @description 启动类
 * @date Date : 2022年06月06日 22:42
 **/
@MapperScan("org.example.mapper") //扫描的mapper
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class DemoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        TestSingletonController.context = context;
    }
}
