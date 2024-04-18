package com.application.blog.controller;

import com.application.blog.models.Comment;
import com.application.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<Comment> createComment(@PathVariable Integer userId,@PathVariable Integer postId, @RequestBody Comment comment) throws Exception{
        return new ResponseEntity<>(commentService.createComment(userId,postId,comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentid}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Integer commentid) throws Exception{
        return new ResponseEntity<>(commentService.deleteComment(commentid),HttpStatus.ACCEPTED);
    }

    @PutMapping("/{commentid}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer commentid, @RequestBody Comment comment) throws Exception{
        return new ResponseEntity<>(commentService.updateComment(commentid,comment),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> getAllComment(@PathVariable Integer postId) throws Exception{
        return new ResponseEntity<>(commentService.getAllComment(postId),HttpStatus.ACCEPTED);
    }


}
