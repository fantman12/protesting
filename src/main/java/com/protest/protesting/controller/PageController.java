package com.protest.protesting.controller;

import com.protest.protesting.entity.User;
import com.protest.protesting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    UserService userService;

    private User user1;

    @GetMapping("/loginPage")
    public String loginPage() {
        return "login";
    }


    private void setup() {
        user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("pass1");
        user1.setAccountNonExpired(true);
        user1.setAccountNonLocked(true);
        user1.setName("USER1");
        user1.setCredentialsNonExpired(true);
        user1.setEnabled(true);
        user1.setAuthorities(AuthorityUtils.createAuthorityList("USER"));
    }

    @GetMapping("/userRun")
    public void run() {
        System.out.println("11");

        user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("pass1");
        user1.setAccountNonExpired(true);
        user1.setAccountNonLocked(true);
        user1.setName("USER1");
        user1.setCredentialsNonExpired(true);
        user1.setEnabled(true);
        user1.setAuthorities(AuthorityUtils.createAuthorityList("USER"));

        userService.createUser(user1);


        System.out.println("123123");
    }
}
