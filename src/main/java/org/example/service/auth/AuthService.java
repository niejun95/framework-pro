package org.example.service.auth;

import org.example.entity.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description 登录功能
 * @author ryan
 * @date 2023/4/25 9:30
 * @version 1.0
 */
public interface AuthService {
    void sendSmsCaptcha(String phone);

    Account verifySmsCaptcha(String phone, String smsCaptcha);

    void logout(HttpServletRequest request);

    void rememberMeLogin(String phone, String frontSmsCaptcha, HttpServletResponse response);

    String simpleLogin(String phone, String frontSmsCaptcha);

}
