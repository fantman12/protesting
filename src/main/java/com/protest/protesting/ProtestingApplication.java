package com.protest.protesting;

import com.protest.protesting.mapper.AccountMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.protest.protesting.mapper")
public class ProtestingApplication implements CommandLineRunner {

    @Autowired
    private AccountMapper accountMapper;

    public static void main(String[] args) {
        SpringApplication.run(ProtestingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Start Time:" + accountMapper.getTime());
    }
}
