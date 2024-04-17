package com.application.blog.services;

import com.application.blog.models.Post;
import com.application.blog.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepo postRepo;

    @Override
    public Post createPost(Post post){
        return postRepo.save(post);
    }

    @Override
    public Post  updatePost( Post post ,  Integer postId) throws Exception {
        Optional<Post> postTem = postRepo.findById(postId);

        if(postTem.isPresent()) {

            Post oldPost = postTem.get();
            oldPost.setTitle(post.getTitle());
            oldPost.setDesc(post.getDesc());

            return postRepo.save(oldPost);

        }else {
            throw new Exception("Unable to find post with this id "+postId);
        }
    }

    @Override
    public Post deletePosts(Integer postId) throws Exception {

        Optional<Post> postTem = postRepo.findById(postId);

        if(postTem.isPresent()) {

            postRepo.deleteById(postId);
            return postTem.get();
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

}
