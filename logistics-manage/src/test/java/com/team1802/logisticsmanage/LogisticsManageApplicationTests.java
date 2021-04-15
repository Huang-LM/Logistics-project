package com.team1802.logisticsmanage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class LogisticsManageApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("email","code",60,TimeUnit.SECONDS);

    }

    @Test
    void test(){
        System.out.println(redisTemplate.opsForValue().get("email"));
    }

}
