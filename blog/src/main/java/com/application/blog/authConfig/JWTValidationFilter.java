package com.application.blog.authConfig;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


public class JWTValidationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt= request.getHeader(SecurityConstants.JWT_HEADER);
        System.out.println("Checking for Jwt");

        if(jwt != null) {

            try {
                System.out.println("entered in try block");
                //extracting the word Bearer
                jwt = jwt.substring(7);


                SecretKey key= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());



                Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

                System.out.println("Tring to get username");
                String username= String.valueOf(claims.get("username"));

                System.out.println(username);
                System.out.println(claims);

                String role= (String)claims.get("role");
                System.out.println("checking for role");
                List<GrantedAuthority> authorities = new ArrayList<>();
                System.out.println("checking for list");
                 claims.get("roles");
                 List<String> allRoles=(List<String>)claims.get("roles");
                System.out.println(allRoles.get(0));

                authorities.add(new SimpleGrantedAuthority(allRoles.get(0)));
                System.out.println("checking for auth");
                Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                throw new BadCredentialsException("Invalid Token received..");
            }



        }

        filterChain.doFilter(request, response);
    }



    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        System.out.println("for others called");
        return request.getServletPath().equals("/api/user/login");
    }



}
