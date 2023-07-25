package org.example.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.handler.EncryptTypeHandler;
import org.example.handler.GenderTypeHandler;

import java.io.Serializable;

/**
 * @author niejun
 * @version 1.0
 * @className User
 * @date 2022/6/7
 * @description
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user", autoResultMap = true)
public class User implements Serializable {

    private Long id;

    private String userName;

    @TableField(typeHandler = EncryptTypeHandler.class)
    private String password;

    @TableField(typeHandler = GenderTypeHandler.class)
    private String gender;

    private Integer age;

    private String address;

    private String idNo;

}
