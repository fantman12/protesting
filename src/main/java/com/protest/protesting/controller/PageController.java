package com.protest.protesting.controller;

import com.protest.protesting.entity.User;
import com.protest.protesting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

@Controller
public class PageController {
    @Autowired UserService userService;

    private User user1;


    @GetMapping(value = "/loginPage")
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
//        System.out.println(Collections.singletonList("ROLE_USER"));


        user1 = new User();
        user1.setUsername("user7");
        user1.setPassword("pass7");
        user1.setRoles("ROLE_USER");
        user1.setAccountNonExpired(true);
        user1.setAccountNonLocked(true);
        user1.setName("USER1");
        user1.setCredentialsNonExpired(true);
        user1.setEnabled(true);
        user1.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));

        userService.createUser(user1);

        Collection<? extends GrantedAuthority> authorities1 = user1.getAuthorities();
        Iterator<? extends GrantedAuthority> it = authorities1.iterator();
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) user1.getAuthorities();


        System.out.println("123123");
    }


}
