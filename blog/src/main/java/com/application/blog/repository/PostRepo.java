package com.application.blog.repository;

import com.application.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Integer> {
    public Post findByPostId(Integer postId);
}
