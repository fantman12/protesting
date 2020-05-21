package com.protest.protesting.controller;

import com.protest.protesting.entity.AccountEntity;
import com.protest.protesting.mapper.AccountMapper;
import com.protest.protesting.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

@RestController
public class HelloSpringBootController {

    @Autowired
    private AccountMapper accountMapper;

    @RequestMapping(value="/hello")
    public String hellSpringBoot() {
        return "hello";
    }

    @GetMapping(value = "/getNow")
    public String getServerTime() {
        return accountMapper.getTime();
    }


    @GetMapping(value = "/getAccount")
    public Object getAccount(@RequestParam(value = "id") String id) {
        return accountMapper.getUser(id);
    }

    @GetMapping("/targetPing")
    public void pubPing() {
        Jedis jedis = new RedisUtil().getInstance();
        jedis.publish("tping", "one");

        jedis.close();
    }

    @GetMapping("/targetPong")
    public void subPong() {
        Jedis jedis = new RedisUtil().getInstance();

        jedis.subscribe(new JedisPubSub() {
        @Override
        public void onMessage(String channel, String message) {
            System.out.println(channel);
            System.out.println(message);
        }}, "tping");

        jedis.close();
    }

}
