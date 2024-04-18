package com.application.blog.services;

import com.application.blog.dto.PostDTO;
import com.application.blog.models.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    public Post getPostsById(Integer postId) throws Exception;
    public String deletePosts(Integer postId) throws Exception;

    Post createPost(PostDTO postDTO, Integer userId) throws Exception;

    public Post  updatePost(PostDTO post , Integer postId) throws Exception;

    public List<Post> getAllPost() throws Exception;

}
