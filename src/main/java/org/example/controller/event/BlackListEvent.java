package org.example.controller.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author ryan
 * @version 1.0.0
 * @description 自定义黑名单处理事件
 * @date 2023/07/08 16:45:22
 */
@Getter
@Setter
public class BlackListEvent extends ApplicationEvent {

    private String phone;

    public BlackListEvent(Object source, String phone) {
        super(source);
        this.phone = phone;
    }
}
