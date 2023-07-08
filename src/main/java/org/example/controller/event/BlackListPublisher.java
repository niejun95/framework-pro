package org.example.controller.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class BlackListPublisher {

    private ApplicationEventPublisher publisher;

    public void publisher(String phone) {
        BlackListEvent blackListEvent = new BlackListEvent(this, phone);
        publisher.publishEvent(blackListEvent);
        log.info("黑名单事件发布成功 - 手机号：{}", phone);
    }
}
