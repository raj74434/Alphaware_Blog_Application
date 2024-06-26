package com.application.blog.services;

import com.application.blog.authConfig.SecurityConstants;
import com.application.blog.dto.UserDTO;
import com.application.blog.models.Users;
import com.application.blog.repository.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(phone, password)
            );

            // Get authorities from the authenticated user
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            System.out.println("entered in try block");
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(phone,password)
//            );
            System.out.println("now going to ghenerate jwt");

            SecretKey secretKey= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());  //created a key

            System.out.println(authorities);
            String jwt= Jwts.builder().
                    setIssuer("Raj")
                    .setSubject("Jwt tocken")  //these two are optional
                    .claim("username",phone)
//    It call method  ===>populateAuthorities <====  which return a string with comma seprated eg=> ADMIN,CUSTOMER
                    .claim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
// for multiple roles =>    .claim("role",populateAuthorities(authentication.getAuthorities()))
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
    public List<Users> getAllUsers() throws Exception {
        return userRepo.findAll();
    }

    @Override
    public Users getUserById( Integer userId) throws Exception {
        Optional<Users> userOptional=userRepo.findById(userId);

        if(userOptional.isPresent()){
            return userOptional.get();
        }
        else throw new Exception("No User found with this id "+userId);

    }

    @Override
    public String deleteUserById( Integer userId) throws Exception{
        Optional<Users> userOptional=userRepo.findById(userId);
        if(userOptional.isPresent()){
            try{
                userRepo.deleteById(userId);
                return "sucess";
            }
            catch (Exception exception){
                throw new Exception("unable to delete user with id "+userId);
            }

        }
        else throw new Exception("No User found with this id "+userId);

    }






    @Override
    public Users findUserByNumber(String password,String number) {
        System.out.println(password);
      return  userRepo.findByPhone(number);
    }




}
