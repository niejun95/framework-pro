package org.example.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("account")
public class Account {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer money;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "low_income_flag")
    private String lowIncomeFlag;

    public Account(Integer id, String name, Integer money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }
}
