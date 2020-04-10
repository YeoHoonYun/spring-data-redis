package com.baeldung.spring.data.redis.controller;

import com.baeldung.spring.data.redis.queue.RedisMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class SpringDataRedisController {

    @Autowired
    RedisMessagePublisher redisMessagePublisher;

    @GetMapping("/save")
    public ResponseEntity<String> save(@RequestParam String firstName) {
        Random r = new Random();
        List<String> queueList = Arrays.asList("test_yun01", "test_yun02", "test_yun03");

        int index = r.nextInt(queueList.size());
        redisMessagePublisher.publish(firstName, queueList.get(index));

        return ResponseEntity.ok("true");
    }
}
