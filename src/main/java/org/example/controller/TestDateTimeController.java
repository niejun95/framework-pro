package org.example.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entities.Account;
import org.example.entities.AnotherUser;
import org.example.mapper.AccountMapper;
import org.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

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

    @Autowired
    private UserMapper userMapper;

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

    @RequestMapping("/queryUserOOO")
    public void queryUserOOO() {
        long current = System.currentTimeMillis();    //当前时间毫秒数
        // 今天零点零分零秒的毫秒数
        long zeroT = current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();
        Date startDate = new Date(zeroT);

        // 今天23点59分59秒的毫秒数
        long endT = zeroT+24*60*60*1000-1;
        Date endDate = new Date(endT);

        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("startDate", startDate);
        requestParam.put("endDate", endDate);

        Date date = new Date();
        List<AnotherUser> userList = userMapper.queryUserByCreateTime(requestParam);

        log.info("结果：{}", userList.size());
    }




}
