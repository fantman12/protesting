package com.protest.protesting;

import com.protest.protesting.mapper.AccountMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import com.protest.protesting.utils.RedisUtil;
import redis.clients.jedis.JedisPubSub;

@SpringBootApplication
//@MapperScan("com.protest.protesting.mapper")
public class ProtestingApplication implements CommandLineRunner {

    @Autowired
    private AccountMapper accountMapper;

    public static String Schannel = null;
    public static String Smessage = null;

    public static void main(String[] args) {
        SpringApplication.run(ProtestingApplication.class, args);
//        subPing();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Start Time:" + accountMapper.getTime());
    }


    public static void redisConnect() {
        String IP = "localhost";
        int PORT = 6379;
        int TIMEOUT = 1000;


        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, IP, PORT, TIMEOUT);
        Jedis jedis = jedisPool.getResource();

        jedis.publish("blockChannel", "target pong");


    }

    public static void subPing() {
        Jedis jedis = new RedisUtil().getInstance();
        Jedis SetJedis = new RedisUtil().getInstance();

        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println(channel);
                System.out.println(message);

                Schannel = channel;
                Smessage = message;

                SetJedis.hset(Schannel, Smessage, "data init");
            }}, "tping"
        );


        // DB X
        // Reids HASH key 1 value 2 subValue
        // DB Connection

        // Reids HASH key 1 value 2 subValue 1번 유저 2번 유저
        // HASH ( 모호 )
        // 게임이 진행되가면서 데이터 1번 유저 2번 유저 ~~~~ 데이터 결과를 내야되잔아

        // 결과를 정리어케하지 존나 어렵네

        // 오목 1 유저 2 유저~~
        // 33 34 1유저 이겻어
        // 간단?
        // ?




        jedis.close();
    }
}
