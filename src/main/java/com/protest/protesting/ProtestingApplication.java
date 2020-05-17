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

@SpringBootApplication
//@MapperScan("com.protest.protesting.mapper")
public class ProtestingApplication /*implements CommandLineRunner*/ {

    @Autowired
    private AccountMapper accountMapper;

    public static void main(String[] args) {
         SpringApplication.run(ProtestingApplication.class, args);
         redisConnect();
    }

//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("Start Time:" + accountMapper.getTime());
//    }


    public static void redisConnect() {
        String IP = "localhost";
        int PORT = 6379;
        int TIMEOUT = 1000;


        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, IP, PORT, TIMEOUT);
        Jedis jedis = jedisPool.getResource();

//        jedis.set("test", "1234");
//        System.out.println(jedis.get("test"));

        jedis.publish("blockChannel", "target pong");

//        jedis.close();
//        jedisPool.close();

    }
}
