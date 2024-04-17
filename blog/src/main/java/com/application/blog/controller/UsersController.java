package com.application.blog.controller;

import com.application.blog.dto.UserDTO;
import com.application.blog.models.Users;
import com.application.blog.services.UsersService;
import com.application.blog.services.UsersServiceImpl;
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
//@OpenAPIDefinition(info = @Info(title = "REST API", version = "1.1"),
//        security = {
//                @SecurityRequirement(name = "basicAuth"),
//                @SecurityRequirement(name = "bearerToken")
//        }
//)
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private UsersService usersService;


    @GetMapping ("/test")
    public ResponseEntity<String> test(){
        System.out.println("test called");
        return new ResponseEntity<>("Yes", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users users){
        return new ResponseEntity<>(usersService.registerUser(users), HttpStatus.CREATED);
    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> registerUser(@RequestBody UserDTO users) throws ServletException {
//        System.out.println("Login called");
//        return new ResponseEntity<>(usersService.loginUser(users), HttpStatus.CREATED);
//    }

    @GetMapping ("/test2")
    public ResponseEntity<String> test2(){
        System.out.println("test2 called");
        return new ResponseEntity<>("Yes2", HttpStatus.OK);
    }

}
