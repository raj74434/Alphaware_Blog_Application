package com.application.blog.models;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Post post;

    @OneToOne
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Users users;
}
