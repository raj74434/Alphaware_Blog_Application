package com.application.blog.controller;

import com.application.blog.dto.PostDTO;
import com.application.blog.models.Post;
import com.application.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/{userId}")
    public ResponseEntity<Post>  createNewPost(@RequestBody PostDTO postDTO,@PathVariable Integer userId) throws Exception {

        return new ResponseEntity<>(postService.createPost(postDTO,userId), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post>  getPost(@PathVariable Integer postId) throws Exception {

        return new ResponseEntity<>(postService.getPostsById(postId), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>>  getAllPost() throws Exception {

        return new ResponseEntity<>(postService.getAllPost(), HttpStatus.CREATED);
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<String>  deletePost(@PathVariable Integer postId) throws Exception {

        return new ResponseEntity<>(postService.deletePosts(postId), HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post>  updatePost(@RequestBody PostDTO postDTO ,@PathVariable Integer postId) throws Exception {

        return new ResponseEntity<>(postService.updatePost(postDTO,postId), HttpStatus.CREATED);
    }

}
