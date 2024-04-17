package com.application.blog.services;

import com.application.blog.authConfig.SecurityConstants;
import com.application.blog.dto.UserDTO;
import com.application.blog.models.Users;
import com.application.blog.repository.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager manager;

    @Override
    public Users registerUser(Users users){
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepo.save(users);
    }

//    @Override
//    public String loginUser(UserDTO userDTO) throws ServletException {
//
//        System.out.println("yes");
//
////        this.doAuthenticate(userDTO.getPhone(), userDTO.getPassword());
//
////        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//
////        System.out.println("yes");
////        System.out.println(authentication.getCredentials());
////        System.out.println(authentication.getDetails());
////        System.out.println(authentication.getName());
////        System.out.println(passwordEncoder.encode(userDTO.getPassword()));
////        System.out.println(userDTO.getPassword());
//
//        if(authentication !=null){
//
//            SecretKey secretKey= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());  //created a key
//
//            String jwt= Jwts.builder().
//                    setIssuer("Raj")
//                    .setSubject("Jwt tocken")  //these two are optional
//                    .claim("username",authentication.getName())
//
////    It call method  ===> populateAuthorities <====  which return a string with comma seprated eg=> ADMIN,CUSTOMER
//                    .claim("role",populateAuthorities(authentication.getAuthorities()))
//                    .setIssuedAt(new Date())
//                    .setExpiration(new Date(new Date().getTime()+300000000))
//                    .signWith(secretKey).compact();
//           return jwt;
//
//        }
//        else{
//            throw new ServletException("Wrong username or password");
//        }
//    }


//    private void doAuthenticate(String phone, String password) {
//
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(phone, password);
//        try {
//            manager.authenticate(authentication);
//
//
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException(" Invalid Username or Password  !!");
//        }
//
//    }



    private String populateAuthorities(Collection< ? extends GrantedAuthority> collection){
        String role="";
        for(GrantedAuthority auth:collection){
            role=auth.getAuthority();
        }
        return role;
    }



    @Override
    public Users findUserByNumber(String password,String number) {
        System.out.println(password);
      return  userRepo.findByPhone(number);
    }

}
