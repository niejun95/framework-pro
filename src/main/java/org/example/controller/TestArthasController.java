package org.example.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : niejun
 * @description arthas 定位CPU过高和线程死锁
 * @date : 2022年10月16日 9:46
 **/
@RestController
@RequestMapping("/arthas")
public class TestArthasController {
    public static final Logger log = LogManager.getLogger(TestDateTimeController.class);

    public static final Object obj1 = new Object();
    public static final Object obj2 = new Object();

    @RequestMapping("/cpu")
    public void testCpu() {
        while (true) {
            log.info("CPU up");
        }
    }

    @RequestMapping("/deadLock")
    public void testDeadLock() {
        new Thread(() -> {
            synchronized (obj1) {
                log.info(Thread.currentThread() + " get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " waiting get resource2");
                synchronized (obj2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (obj2) {
                log.info(Thread.currentThread() + " get resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " waiting get resource1");
                synchronized (obj1) {
                    System.out.println(Thread.currentThread() + "get resource1");
                }
            }
        }).start();
    }
}
