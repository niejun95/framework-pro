package org.example.controller.distributelock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.Redisson;
import org.redisson.RedissonLock;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @className TestDistributeLockDemo
 * @author niejun
 * @date 2022/6/30
 * @description 分布式锁demo
 * @version 1.0
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
        log.info("当前实例地址 {}", this);
        log.info("当前线程id {}", Thread.currentThread().getName());
        // redis 查询加锁的信息 hgetall product_101
        String lockKey = "product_101";
        RLock lock =  redisson.getLock(lockKey);
        lock.lock();
        try {
            int stock  = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                redisTemplate.opsForValue().set("stock", realStock + "");
                log.info("扣减成功，剩余库存：{}", realStock);
            } else {
                log.info("扣减失败，库存不足");
            }
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
        log.info("结束了");
    }

    @RequestMapping("decrementStockByRedissonPro")
    public void decrementStockByRedissonPro() {
        // 优化 redisson 分布式锁 使用读写锁
        String lockKey = "product_101";
        RReadWriteLock readWriteLock = redisson.getReadWriteLock(lockKey);
        RLock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            int stock  = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                redisTemplate.opsForValue().set("stock", realStock + "");
                log.info("扣减成功，剩余库存：{}", realStock);
            } else {
                log.info("扣减失败，库存不足");
            }
        } finally {
            writeLock.unlock();
        }
    }

}
