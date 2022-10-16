package org.example.controller;

import org.example.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName: TestMessController
 * @Author: niejun
 * @CreateTime: 2022-07-25  15:36
 * @Description: TestMessController
 * @Version: 1.0
 */
@RestController
@RequestMapping("/mess")
public class TestMessController {

    
    @Autowired
    private TransTest transTest;

    @RequestMapping("/test")
    public void test() {
        Account account = new Account();
        account.setId(1);
        account.setName("ryan");
        account.setMoney(200);
        account.setCreateTime(new Date());

        try {
            transTest.change(account);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(account.getName());
    }

}
