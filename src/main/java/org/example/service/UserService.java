package org.example.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.example.entity.XxlJobInfo;
import org.example.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

/**
 * @author ryan
 * @version 1.0.0
 * @description
 * @date 2023/08/05 11:23:38
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private static final ZoneId ZONE_ID = TimeZone.getDefault().toZoneId();

    private static final String CRON_FORMAT = "ss mm HH dd MM ? yyyy";

    private final UserMapper userMapper;

    private final XxlService xxlService;

    @Transactional
    public void register(User user) {
        if (Objects.isNull(user) || StrUtil.isBlank(user.getUserName()) || StrUtil.isBlank(user.getPassword())) {
            throw new RuntimeException("账号或密码为空");
        }
        userMapper.insert(user);

        LocalDateTime scheduleTime = LocalDateTime.now().plusMinutes(1);
        String cronTime = getCron(scheduleTime);
        log.info("cron time {}", cronTime);

        XxlJobInfo xxlJobInfo = XxlJobInfo.builder().jobDesc("定时给用户打招呼")
                .author("niejun")
                .scheduleType("CRON")
                .scheduleConf(getCron(scheduleTime))
                .glueType("BEAN")
                .executorHandler("sayHelloHandler")
                .executorParam(user.getUserName())
                .misfireStrategy("DO_NOTHING")
                .executorRouteStrategy("FIRST")
                .triggerNextTime(toEpochMilli(scheduleTime))
                .executorBlockStrategy("SERIAL_EXECUTION")
                .triggerStatus(1)
                .build();
        xxlService.addJob(xxlJobInfo);
    }


    public void sayHelloToUser(String username) {
        if (StrUtil.isBlank(username)) {
            log.error("用户名为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        User user = userMapper.selectOne(queryWrapper);
        String message = "Welcome to Java,I am ryan.";
        log.info(user.getUserName() + " , hello, " + message);
    }

    public void pushWeatherNotification() {
        List<User> users = userMapper.selectList(null);
        log.info("执行发送天气通知给用户的任务。。。");
        for (User user : users) {
            log.info(user.getUserName() + "----" + "晴天");
        }
    }

    public static long toEpochMilli(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return 0L;
        }
        return localDateTime.atZone(ZONE_ID).toInstant().toEpochMilli();
    }

    public String getCron(LocalDateTime dateTime) {
        long localDateTime = toEpochMilli(dateTime);
        SimpleDateFormat sdf = new SimpleDateFormat(CRON_FORMAT);
        String formatTimeStr = null;
        if (dateTime != null) {
            formatTimeStr = sdf.format(localDateTime);
        }
        return formatTimeStr;
    }
}
