package com.application.blog.services;

import com.application.blog.models.Comment;

import java.util.List;

public interface CommentService {
    public Comment createComment(Integer userId, Integer postId, Comment comment) throws Exception;

    public Comment deleteComment(Integer commentid) throws Exception;

    public Comment updateComment(Integer commentid, Comment comment) throws Exception;

    public List<Comment> getAllComment(Integer postId) throws Exception;
}
