package org.example.entities;

/**
 * @ClassName Account
 * @Author niejun
 * @Date 2022/6/15
 * @Description: 账户实体类
 * @Version 1.0
 **/
public class Account {
    private Integer id;

    private String name;

    private Integer money;

    public Account() {
    }

    public Account(Integer id, String name, Integer money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
