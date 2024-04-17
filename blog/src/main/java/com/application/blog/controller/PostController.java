package com.application.blog.controller;

import com.application.blog.models.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @PostMapping("/")
    public ResponseEntity<String>  createNewPost(@RequestBody Post post){

        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<String>  getPost(@PathVariable Integer postId){

        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<String>  deletePost(@PathVariable Integer postId){

        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String>  updatePost(@RequestBody Post post ,@PathVariable Integer postId){

        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

}
