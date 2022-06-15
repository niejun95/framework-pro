package org.example.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entities.Account;
import org.example.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestDBController
 * @Author niejun
 * @Date 2022/6/15
 * @Description:
 * @Version 1.0
 **/
@RestController
@RequestMapping("/db")
public class TestDBController {
    public static final Logger log = LogManager.getLogger(TestDBController.class);

    @Autowired
    private AccountMapper accountMapper;

    @RequestMapping("/userQ/{name}")
    public String queryUserInfoByName(@PathVariable String name) {
        log.info("name=" + name);
        Account account = accountMapper.queryAccountInfoByName(name);
        return JSONObject.toJSONString(account);
    }
}
