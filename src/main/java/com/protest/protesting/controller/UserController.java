package com.protest.protesting.controller;

import com.protest.protesting.entity.AuthenticationRequest;
import com.protest.protesting.entity.AuthenticationToken;
import com.protest.protesting.entity.User;
import com.protest.protesting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired AuthenticationManager authenticationManager;
    @Autowired UserService userService;

    @PostMapping(value = "/user/login")
    public AuthenticationToken login(@RequestBody AuthenticationRequest authenticationRequest, HttpSession session) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        System.out.println(username);
        System.out.println(password);

        UsernamePasswordAuthenticationToken toekn = new UsernamePasswordAuthenticationToken(username, password);
        System.out.println(toekn);
        Authentication authentication = authenticationManager.authenticate(toekn);
        System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println(username);
        System.out.println(password);

        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        User user = userService.readUser(username);

        System.out.println(username);
        System.out.println(password);

        return new AuthenticationToken(user.getName(), user.getAuthorities(), session.getId());



    }
}
