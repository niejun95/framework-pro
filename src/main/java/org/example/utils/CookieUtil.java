package org.example.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @description: cookie 工具类
 * @author: ryan
 * @date 2023/4/25 14:34
 * @version: 1.0
 */
@Slf4j
public class CookieUtil {
    public static String encodeCookie(String[] cookieTokens) {
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < cookieTokens.length; index++) {
            try {
                sb.append(URLEncoder.encode(cookieTokens[index], StandardCharsets.UTF_8.toString()));
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(), e);
            }
            if (index < cookieTokens.length - 1) {
                sb.append(":");
            }
        }
        String value = sb.toString();
        sb = new StringBuilder(new String(Base64.getEncoder().encode(value.getBytes())));
        while (sb.charAt(sb.length() - 1) == '=') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String[] decodeCookie(String cookieValue) {
        for (int j = 0; j < cookieValue.length() % 4; j++) {
            cookieValue += "=";
        }

        String cookieAsPlainText = new String(Base64.getDecoder().decode(cookieValue.getBytes()));
        String[] tokens = StringUtils.delimitedListToStringArray(cookieAsPlainText, ":");
        for (int i = 0; i < tokens.length; i++) {
            try {
                tokens[i] = URLDecoder.decode(tokens[i], StandardCharsets.UTF_8.toString());
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(), e);
            }
        }
        return tokens;
    }
}
