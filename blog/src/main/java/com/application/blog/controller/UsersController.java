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

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private UsersService usersService;
    

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users users){
        return new ResponseEntity<>(usersService.registerUser(users), HttpStatus.CREATED);
    }
//
    @PostMapping("/login")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO users) throws Exception {

        return new ResponseEntity<>(usersService.loginUser(users), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Users>> getAllUsers() throws Exception {
       return new ResponseEntity<>(usersService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer userId) throws Exception {
        return new ResponseEntity<>(usersService.getUserById(userId),HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer userId) throws Exception {
        return new ResponseEntity<>(usersService.deleteUserById(userId),HttpStatus.ACCEPTED);
    }


}
