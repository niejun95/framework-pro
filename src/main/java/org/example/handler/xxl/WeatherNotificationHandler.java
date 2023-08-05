package org.example.handler.xxl;

import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import org.example.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @author ryan
 * @version 1.0.0
 * @description 给所有用户发送消息通知
 * @date 2023/08/05 11:22:25
 */
@Component
@RequiredArgsConstructor
public class WeatherNotificationHandler extends IJobHandler {

    private final UserService userService;

    @XxlJob(value = "weatherNotificationHandler")
    @Override
    public void execute() {
        userService.pushWeatherNotification();
    }
}
