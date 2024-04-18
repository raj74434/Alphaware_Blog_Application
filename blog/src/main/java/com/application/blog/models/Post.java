package com.application.blog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    private String title;

    private  String description;

    private LocalDate date;

    @OneToMany(mappedBy = "post")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Comment> comments=new ArrayList<>();

    @ManyToMany(mappedBy = "posts")
    @JsonIgnore
    private List<Category> categories=new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Users users;

}
