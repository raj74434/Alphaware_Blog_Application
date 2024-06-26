package com.application.blog.services;

import com.application.blog.dto.PostDTO;
import com.application.blog.models.Post;
import com.application.blog.models.Users;
import com.application.blog.repository.PostRepo;
import com.application.blog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public Post createPost(PostDTO postDTO, Integer userId) throws Exception {

           Optional<Users>userTem= userRepo.findById(userId);


            Post post=new Post();
        if(userTem.isPresent()){
            post.setUsers(userTem.get());
            post.setDescription(postDTO.getDescription());
            post.setTitle(postDTO.getTitle());
            post.setDate(LocalDate.now());

            return postRepo.save(post);
        }
        else{
            throw new Exception("No user found with this id"+userId);
        }

    }

    @Override
    public Post  updatePost( PostDTO postDTO ,  Integer postId) throws Exception {
        Optional<Post> postTem = postRepo.findById(postId);

        if(postTem.isPresent()) {

            Post oldPost = postTem.get();
            oldPost.setTitle(postDTO.getTitle());
            oldPost.setDescription(postDTO.getDescription());
            oldPost.setDate(LocalDate.now());

            return postRepo.save(oldPost);

        }else {
            throw new Exception("Unable to find post with this id "+postId);
        }
    }

    @Override
    public String deletePosts(Integer postId) throws Exception {

        Optional<Post> postTem = postRepo.findById(postId);

        if(postTem.isPresent()) {

            postRepo.deleteById(postId);
            return "deletion successful";
        }else {
            throw new Exception("Post doesn't exist with this id "+postId);
        }

    }

    @Override
    public Post getPostsById(Integer postId) throws Exception {

        Optional<Post> postTem = postRepo.findById(postId);

        if(postTem.isPresent()) {

            return postTem.get();

        }else {
            throw new Exception("Post doesn't exist with this id "+postId);
        }

    }

    public List<Post> getAllPost() throws Exception{
        return postRepo.findByDate(LocalDate.now());
    }

}
