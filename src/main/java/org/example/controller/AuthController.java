package org.example.controller;

import org.example.service.auth.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: ryan
 * @date 2023/4/25 13:42
 * @version: 1.0
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/sendCaptcha/{phone}")
    public void sendSmsCaptcha(@PathVariable String phone) {
        authService.sendSmsCaptcha(phone);
    }

    @GetMapping("/simpleLogin")
    public String simpleLogin(String phone, String frontSmsCaptcha) {
        String token = authService.simpleLogin(phone, frontSmsCaptcha);
        return token;
    }

    @GetMapping("/rememberLogin")
    public void proLogin(String phone, String frontSmsCaptcha, HttpServletResponse response) {
        authService.rememberMeLogin(phone, frontSmsCaptcha, response);
    }
}
