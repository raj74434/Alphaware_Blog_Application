package com.application.blog.services;

import com.application.blog.dto.UserDTO;
import com.application.blog.models.Users;
import jakarta.servlet.ServletException;
import org.springframework.security.core.Authentication;

public interface UsersService {

    public Users registerUser(Users users);

//    public String loginUser(UserDTO userDTO) throws ServletException;

    public Users findUserByNumber(String password,String number);

}
