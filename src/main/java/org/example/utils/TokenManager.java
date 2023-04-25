package org.example.utils;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.config.JwtConfig;
import org.example.constants.AuthConstants;
import org.example.entities.Account;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description: jwt 工具类
 * @author: ryan
 * @date 2023/4/25 10:45
 * @version: 1.0
 */
@Component
public class TokenManager {
    @Resource
    JwtConfig jwtConfig;

    public String generateToken(Account account) throws Exception {
        return generateToken(account, 0);
    }

    public String generateToken(Account account, int expire) throws Exception {
        if (expire <= 0) {
            expire = AuthConstants.TOKEN_EXPIRE_TIME;
        }
        long expireTime = System.currentTimeMillis() + expire * 60 * 1000;
        Date date = new Date(expireTime);

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "RS256");
        map.put("typ", "jwt");

        return Jwts.builder().setHeader(map)
                .claim(AuthConstants.JWT_LOGIN_INFO_KEY, JSON.toJSONString(account))
                .setId(UUID.randomUUID().toString())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.RS256, getPrivateKey())
                .compact();
    }

    private PrivateKey getPrivateKey() throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(jwtConfig.getPrivateKey()));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(spec);
    }
}
