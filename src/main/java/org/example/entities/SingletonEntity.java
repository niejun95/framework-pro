package org.example.entities;

import org.springframework.stereotype.Component;

/**
 * @ClassName: SingletonEntity
 * @Author: niejun
 * @CreateTime: 2022-07-18  15:36
 * @Description: 用于测试单例的实体类
 * @Version: 1.0
 */
@Component
public class SingletonEntity {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}