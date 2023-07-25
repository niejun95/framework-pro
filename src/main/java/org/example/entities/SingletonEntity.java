package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author niejun
 * @version 1.0
 * @className SingletonEntity
 * @createTime 2022-07-18  15:36
 * @description 用于测试单例的实体类
 */
@Component
@Getter
@Setter
public class SingletonEntity {

    private String name;

    private Integer age;
}
