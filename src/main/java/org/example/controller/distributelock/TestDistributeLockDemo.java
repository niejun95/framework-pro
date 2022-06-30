package org.example.controller.distributelock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestDistributeLockDemo
 * @Author niejun
 * @Date 2022/6/30
 * @Description: 分布式锁demo
 * @Version 1.0
 **/
@RequestMapping("/redis")
@RestController
public class TestDistributeLockDemo {
    public static final Logger log = LogManager.getLogger(TestDistributeLockDemo.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private Redisson redisson;

    @RequestMapping("/decrementStock")
    public void decrementStock() {
        String lockKey = "product_101";
        String clientId = UUID.randomUUID().toString();
        Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 30, TimeUnit.SECONDS);

        if (!result) {
            log.info("加锁失败");
            return ;
        }
        try {
            int stock  = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                redisTemplate.opsForValue().set("stock", realStock + "");
                log.info("扣减成功，剩余库存：{}", realStock);
            } else {
                log.info("扣减失败，库存不足");
            }
        } finally {
            // 删除前判断是否 是本机加的锁   ！！！两个操作写成原子性
            if (clientId.equals(redisTemplate.opsForValue().get(lockKey))) {
                redisTemplate.delete(lockKey);
            }
        }

    }

    @RequestMapping("/decrementStockByRedisson")
    public void decrementStockByRedisson() {
        String lockKey = "product_101";
        RLock lock = redisson.getLock(lockKey);
        try {
            lock.lock();
            int stock  = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                redisTemplate.opsForValue().set("stock", realStock + "");
                log.info("扣减成功，剩余库存：{}", realStock);
            } else {
                log.info("扣减失败，库存不足");
            }
        } finally {
            lock.unlock();
        }
    }

}
