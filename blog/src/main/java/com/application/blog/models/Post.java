package com.application.blog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    private String title;

    private  String desc;

    @OneToMany(mappedBy = "post")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Comment> comments=new ArrayList<>();

    @ManyToMany(mappedBy = "posts")
    private List<Category> categories=new ArrayList<>();

    @ManyToOne
    private Users users;

}
