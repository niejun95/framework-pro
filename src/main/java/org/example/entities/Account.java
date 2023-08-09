package org.example.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author niejun
 * @version 1.0
 * @className Account
 * @date 2022/6/15
 * @description 账户实体类
 **/
@Data
@NoArgsConstructor
@ToString
public class Account {
    private Integer id;

    private String name;

    private Integer money;

    private Date createTime;

    private String lowIncomeFlag;

    public Account(Integer id, String name, Integer money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }
}
