package com.application.blog.services;

import com.application.blog.authConfig.SecurityConstants;
import com.application.blog.dto.UserDTO;
import com.application.blog.models.Users;
import com.application.blog.repository.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
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
    private AuthenticationManager authenticationManager;

    @Override
    public Users registerUser(Users users){
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepo.save(users);
    }

    @Override
    public String loginUser(UserDTO userDTO) throws Exception {

        System.out.println("yes");
        String phone =userDTO.getPhone();
        String password=userDTO.getPassword();
        System.out.println(phone+"    "+ password);
//        this.doAuthenticate(userDTO.getPhone(), userDTO.getPassword());

//        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

//        System.out.println("yes");
//        System.out.println(authentication.getCredentials());
//        System.out.println(authentication.getDetails());
//        System.out.println(authentication.getName());
//        System.out.println(passwordEncoder.encode(userDTO.getPassword()));
//        System.out.println(userDTO.getPassword());

        try {
            System.out.println("entered in try block");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(phone,password)
            );
            System.out.println("now going to ghenerate jwt");

            SecretKey secretKey= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());  //created a key

            String jwt= Jwts.builder().
                    setIssuer("Raj")
                    .setSubject("Jwt tocken")  //these two are optional
                    .claim("username",phone)

//    It call method  ===> populateAuthorities <====  which return a string with comma seprated eg=> ADMIN,CUSTOMER
//                    .claim("role",populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+300000000))
                    .signWith(secretKey).compact();
            return jwt;
        }
        catch (AuthenticationException e) {
            throw new Exception("Incorrect username or password", e);
        }






    }





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
