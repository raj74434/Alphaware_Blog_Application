package com.application.blog.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String userType;

    private String name;

    @Column(unique = true)
    private String phone;

    @OneToMany(mappedBy = "users")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Post> posts=new ArrayList<>();

    @OneToMany(mappedBy = "users")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Category> category=new ArrayList<>();



    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
