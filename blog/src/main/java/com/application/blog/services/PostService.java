package com.application.blog.services;

import com.application.blog.models.Post;

public interface PostService {
    public Post createPost(Post post);
    public Post getPostsById(Integer postId) throws Exception;
    public Post deletePosts(Integer postId) throws Exception;
    public Post  updatePost( Post post ,  Integer postId) throws Exception;
}
