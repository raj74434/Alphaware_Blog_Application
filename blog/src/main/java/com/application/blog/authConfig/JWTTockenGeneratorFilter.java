package com.application.blog.authConfig;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;


public class JWTTockenGeneratorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("generating jwt");
//        When all authentication steps are done then this authentication object is not null and
//        this filter invoke  before giving response to user.

//        If user provide wrong password or user name then it will be null;
//        This filter fetch from context
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        if(authentication !=null){
            System.out.println("1");
            SecretKey secretKey= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());  //created a key
            System.out.println("2");
            String jwt= Jwts.builder().
                    setIssuer("Raj")
                    .setSubject("Jwt tocken")  //these two are optional
                    .claim("username",authentication.getName())

//    It call method  ===> populateAuthorities <====  which return a string with comma seprated eg=> ADMIN,CUSTOMER
                    .claim("role",populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+300000000))
                    .signWith(secretKey).compact();
            System.out.println(jwt);
            response.setHeader(SecurityConstants.JWT_HEADER,jwt);
            ;

        }
        else{
            throw new ServletException("Wrong username or password");
        }

    }

    private String populateAuthorities(Collection< ? extends GrantedAuthority>  collection){
        String role="";
        for(GrantedAuthority auth:collection){
            role=auth.getAuthority();
        }
        return role;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException{
        System.out.println("login  called");
        return !request.getServletPath().equals("/api/user/login");
    }


}
