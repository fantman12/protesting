package com.protest.protesting.controller;

import com.protest.protesting.entity.AuthenticationRequest;
import com.protest.protesting.entity.AuthenticationToken;
import com.protest.protesting.entity.User;
import com.protest.protesting.service.UserService;
import com.protest.protesting.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@RestController
public class UserController {
    @Autowired AuthenticationManager authenticationManager;
    @Autowired UserService userService;

    @Autowired private JwtUtils jwtUtils;

    @PostMapping(value = "/login")
    public AuthenticationToken login(@RequestBody AuthenticationRequest authenticationRequest, HttpSession session) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        UsernamePasswordAuthenticationToken toekn = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(toekn);

//        System.out.println("!!!");
//        System.out.println(authentication);

        // Security Context Holder에 저장후 추후 뺼수 있음
        SecurityContextHolder.getContext().setAuthentication(authentication);


        // 세션 세팅은 불필요 (Jwt로 유지 예정)
        // session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        User user = userService.readUser(username); // UserDetailsService의 readUser 활용
        // Jwt 형태
        String jwt = jwtUtils.createToken(username, user.getRoles());


        System.out.println(user.getAuthorities());


//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Boolean istrue = authorities.stream().filter(o -> o.getAuthority().equals("ROLE_USER")).findAny().isPresent();


//        System.out.println(istrue);



        // Session 유지 형태
        // return new AuthenticationToken(user.getName(), user.getAuthorities(), session.getId());

        // Jwt 리턴 구조 형태
        return new AuthenticationToken(user.getName(), user.getAuthorities(), jwt);
    }
}
