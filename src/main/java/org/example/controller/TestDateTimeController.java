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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TestDateTimeController
 * @Author niejun
 * @Date 2022/6/21
 * @Description: mybatis mysql 时间类型
 * @Version 1.0
 **/
@RestController
@RequestMapping("/db")
public class TestDateTimeController {
    public static final Logger log = LogManager.getLogger(TestDateTimeController.class);

    @Autowired
    private AccountMapper accountMapper;

    @RequestMapping("/queryAccountByTime/{time}")
    public String queryAccountByTime(@PathVariable String time) {
        log.info("时间：" + time);
        LocalDate date = LocalDate.parse(time);
        List<Account> accounts = accountMapper.queryAccountByTime(date);
        return JSONObject.toJSONString(accounts);
    }

    @RequestMapping("/updateLocalDate/{time}")
    public void updateLocalDate(@PathVariable String time) {
        log.info("时间：" + time);
        LocalDate date = LocalDate.parse(time);
        accountMapper.updateLocalDate(date);
    }

    @RequestMapping("/updateDate/{time}")
    public void updateDate(@PathVariable String time) {
        log.info("时间：" + time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(time);
            accountMapper.updateDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
