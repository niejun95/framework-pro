package org.example.controller.gerneral;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @className TestWatchDogController
 * @author ryan
 * @createTime 2022-09-23  09:00
 * @description 手动实现看门狗机制
 * @version 1.0
 */
@RestController
public class TestWatchDogController {
    public static final Logger log = LogManager.getLogger(TestWatchDogController.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String productId = "MT20220923";

    public static final String lockKey = "MT";

    private static final Integer Timeout = 60;

    private ScheduledExecutorService executorService;

    private List<ExpireEntity> list = new CopyOnWriteArrayList<>();

    @PostConstruct
    public void init() {
        redisTemplate.opsForValue().set(productId, "100");
    }

    @PostConstruct
    public void init2() {
        executorService = Executors.newScheduledThreadPool(1);
        //编写续期的lua
        String expireLua = "if redis.call('get',KEYS[1]) == ARGV[1] " +
                "then if redis.call('ttl', KEYS[1]) < 2/3 * ARGV[2] " +
                "then redis.call('expire',KEYS[1],ARGV[2]); " +
                "return true " +
                "else return false " +
                "end " +
                "else return false " +
                "end ";
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Iterator<ExpireEntity> iterator = list.iterator();
                while (iterator.hasNext()) {
                    ExpireEntity entity = iterator.next();
                    String requestId = entity.getKey();
                    Integer expire = entity.getExpire();

                    redisTemplate.execute(new RedisCallback<Boolean>() {
                        @Override
                        public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                            Boolean eval = false;
                            try {
                                eval = redisConnection.eval(
                                        expireLua.getBytes(),
                                        ReturnType.BOOLEAN,
                                        1,
                                        lockKey.getBytes(),
                                        requestId.getBytes(),
                                        expire.toString().getBytes()
                                );
                                if (!eval) {
                                    log.info("续期失败，未到触发时间");
                                } else {
                                    log.info("续期成功");
                                }
                            } catch (Exception e) {
                                log.error("锁续期失败,{}",e.getMessage());
                            }
                            return eval;
                        }
                    });

                }
            }
        },0,1,TimeUnit.SECONDS);
    }

    @PostMapping("/get/maotai")
    public String secKillMaotai() {
        // 生成一个唯一的值 防止错误解锁
        String requestId = UUID.randomUUID().toString() + Thread.currentThread().getId();
        Boolean isLock = redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, Timeout, TimeUnit.SECONDS);
        ExpireEntity entity = new ExpireEntity(requestId, Timeout);
        try {
            if (isLock) {
                list.add(entity);
                Integer count = Integer.valueOf(redisTemplate.opsForValue().get(productId));
                if (count > 0) {
                    redisTemplate.opsForValue().set(productId, String.valueOf(count - 1));
                    log.info("抢到茅台了");
                    TimeUnit.SECONDS.sleep(70);
                    return "ok";
                } else {
                    return  "no";
                }
            }
        } catch (NumberFormatException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            list.remove(entity);
            String unlockLua = "" +
                    "if redis.call('get',KEYS[1]) == ARGV[1] then redis.call('del',KEYS[1]) ; return true " +
                    "else return false " +
                    "end";

            redisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    Boolean eval = redisConnection.eval(
                            unlockLua.getBytes(),
                            ReturnType.BOOLEAN,
                            1,
                            lockKey.getBytes(),
                            requestId.getBytes()
                    );
                    return eval;
                }
            });
        }
        return "dont get lock";
    }


}
class ExpireEntity {
    private String key;
    private Integer expire;

    public ExpireEntity(String key, Integer expire) {
        this.key = key;
        this.expire = expire;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }
}