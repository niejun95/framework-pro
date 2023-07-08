package org.example.controller.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author ryan
 * @version 1.0.0
 * @description 黑名单事件监听器处理
 * @date 2023/07/08 16:45:52
 */
@Slf4j
@Component
public class BlackListListener {

    private String notificationAddress;


    public void setNotificationAddress(String notificationAddress) {
        this.notificationAddress = notificationAddress;
    }


    @EventListener
    public void processBlackList(BlackListEvent event) {
        log.info("手机号 {} 在黑名单中", event.getPhone());
    }
}
