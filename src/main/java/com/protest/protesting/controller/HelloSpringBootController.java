package com.protest.protesting.controller;

import com.protest.protesting.entity.AccountEntity;
import com.protest.protesting.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
