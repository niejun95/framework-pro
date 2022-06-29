package org.example.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utils.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName TestVisitCacheController
 * @Author niejun
 * @Date 2022/6/29
 * @Description: 访问人数、次数统计
 * @Version 1.0
 **/
@RestController
@RequestMapping("/visit")
public class TestVisitCacheController {

    public static final Logger log = LogManager.getLogger(TestRedisController.class);

    @Autowired
    private CacheService cacheService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/statisticPopulation")
    public void getStatisticPopulation() {
        String key = "HYLL\\x00:rca:statisticPopulation";
        log.info("访问人数为{}", redisTemplate.opsForHyperLogLog().size(key));
    }

    @RequestMapping("/statisticTimes")
    public String getStatisticTimes() {
        String key = "rca:statisticTimes";
        return cacheService.get(key);
    }

    @RequestMapping("/visit")
    public void visit(HttpServletRequest request) {
        String address = getIpAddress(request);
        log.info("当前请求的IP地址为{}", address);
        String key = "HYLL\\x00:rca:statisticPopulation";
        redisTemplate.opsForHyperLogLog().add(key, address);

        key = "rca:statisticTimes";
        if (cacheService.hasKey(key)) {
            cacheService.increment(key);
        } else {
            cacheService.set(key, "1");
        }
    }

    public String getIpAddress(HttpServletRequest request) {
        String ipAddresses = request.getHeader("x-forwarded-for");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getRemoteAddr();
        }
//        if (StringUtils.isNotBlank(ipAddresses)) {
//            sourceIp = ipAddresses.split(",")[0];
//        }

        return ipAddresses;
    }


}
