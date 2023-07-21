package org.example.controller.gerneral;

import com.alibaba.fastjson2.JSONObject;
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
 * @className TestDateTimeController
 * @author niejun
 * @date 2022/6/21
 * @description mybatis mysql 时间类型
 * @version 1.0
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

    @RequestMapping("/createNewUser/{time}")
    public void createUser(@PathVariable String time) {
        log.info("时间：" + time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(time);
            Map<String, Object> requestParam = new HashMap<>();
            requestParam.put("name", "123");
            requestParam.put("gmtCreate", date);
            requestParam.put("gmtModified", date);
            userMapper.insertNewUser(requestParam);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/getCreateTime")
    public void getCreateTime() {
        Date date = userMapper.queryGmtCreateTime();
        log.info("查询到的创建时间为：{}", date);
    }



}