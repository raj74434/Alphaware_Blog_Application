package com.application.blog.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String comment;

    @ManyToOne
    private Post post;

    @OneToOne
    private Users users;
}
