package com.protest.protesting.filter;

import com.protest.protesting.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

//@RequiredArgsConstructor
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        super(authenticationManager);
        this.jwtUtils = jwtUtils;
    }

//    @Override
//    public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        System.out.println("1231231");
//        // 헤더에서 JWT 를 받아옵니다.
//        String token = jwtUtils.resolveToken((HttpServletRequest) request);
//        // 유효한 토큰인지 확인합니다.
//        if (token != null && jwtUtils.validateToken(token)) {
//            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
//            Authentication authentication = jwtUtils.getAuthentication(token);
//            // SecurityContext 에 Authentication 객체를 저장합니다.
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        chain.doFilter(request, response);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        Principal tt = request.getUserPrincipal();
        System.out.println(tt);
        System.out.println("!@@@");



        System.out.println("1231231");
        // 헤더에서 JWT 를 받아옵니다.
//        String token = jwtUtils.resolveToken((HttpServletRequest) request);
        String token = getAuthentication((HttpServletRequest) request);

        System.out.println(token);

        // 유효한 토큰인지 확인합니다.
        if (token != null && jwtUtils.validateToken(token)) {
            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
            Authentication authentication = jwtUtils.getAuthentication(token);
            // SecurityContext 에 Authentication 객체를 저장합니다.

            System.out.println("###");
            System.out.println(authentication);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }


    /**
     * 헤더의 Authorization로부터 토큰을 가져와 사용자의 정보를 담을 Authentication 객체로 변환
     * */
    private String getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (Strings.isEmpty(token)) {
            return null;
        }

        System.out.println(token);
//        Claims claims = jwtUtils.getClaims(token.substring("Bearer ".length()));

        System.out.println(request);
        String resolveToken = jwtUtils.resolveToken((HttpServletRequest) request);

//        System.out.println(resolveToken);

        return token;
    }
}
