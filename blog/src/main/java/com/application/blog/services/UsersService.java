package com.application.blog.services;

import com.application.blog.dto.UserDTO;
import com.application.blog.models.Users;
import jakarta.servlet.ServletException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UsersService {

    public Users registerUser(Users users);

//    public String loginUser(UserDTO userDTO) throws ServletException;

    String loginUser(UserDTO userDTO) throws Exception;

    public List<Users> getAllUsers() throws Exception;

    public Users getUserById( Integer userId) throws Exception;

    public String deleteUserById( Integer userId) throws Exception;

    public Users findUserByNumber(String password, String number);

}
