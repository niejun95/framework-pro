package org.example.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @description 校验实体
 * @author ryan
 * @date 2023/4/27 9:18
 * @version 1.0
 */
@Data
public class ValidUser {

    @NotEmpty(message = "登录账户不能为空")
    @Length(min = 5, max = 16, message = "账号长度为5-16位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;


    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为4-16位")
    private String password;
}
