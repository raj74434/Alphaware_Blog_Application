package com.application.blog.repository;

import com.application.blog.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Integer> {
    Users findByPhone(String phone);
}
