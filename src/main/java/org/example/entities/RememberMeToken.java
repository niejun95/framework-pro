package org.example.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: ryan
 * @date 2023/4/25 14:11
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@TableName("persistent_logins")
public class RememberMeToken {
    @TableField("phone")
    private String phone;

    @TableId
    private String series;

    @TableField("token")
    private String token;

    @TableField("last_used")
    private Date lastUsed;
}
