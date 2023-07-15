package org.example.controller.gerneral;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/cookie")
@Slf4j
public class CookieController {

    @RequestMapping("/generate")
    public void setCookie(HttpServletRequest request, HttpServletResponse response) {
        log.info("生成cookie");
        String id = UUID.randomUUID().toString();
        log.info("生成 uuid {}", id);
        Cookie cookie = new Cookie("id", id);
        /**
         * 设置cookie的过期时间
         *  默认 负数表示不会存储，当浏览器关闭会删除cookie
         *  0表示会被删除
         */
        //cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }

    @RequestMapping("/get")
    public void getCookie(HttpServletRequest request) {
        log.info("获取cookie");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            log.info("cookie key={} , value={}", cookie.getName(), cookie.getValue());
        }
    }
}
