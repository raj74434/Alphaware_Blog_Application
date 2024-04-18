package com.application.blog.repository;

import com.application.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    public Post findByPostId(Integer postId);
    public List<Post> findByDate(LocalDate localDate);
}
