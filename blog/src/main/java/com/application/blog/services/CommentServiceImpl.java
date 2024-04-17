package com.application.blog.services;

import com.application.blog.models.Comment;
import com.application.blog.models.Post;
import com.application.blog.models.Users;
import com.application.blog.repository.CommentRepo;
import com.application.blog.repository.PostRepo;
import com.application.blog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Override
    public Comment createComment(Integer userId, Integer postId, Comment comment) throws Exception {

        Optional<Users> usersTem =  userRepo.findById(userId);

        Optional<Post> postTem =  postRepo.findById(postId);

        if(usersTem.isPresent() && postTem.isPresent()) {
            comment.setUsers(usersTem.get());
            comment.setPost(postTem.get());

            return  commentRepo.save(comment);

        }
        else {
            if(!usersTem.isPresent()) {
                throw new Exception("Unable to find user with this id "+userId);
            }else {
                throw new Exception("Unable to find post with this id "+postId);
            }
        }

    }

    @Override
    public Comment deleteComment(Integer commentid) throws Exception {

        Optional<Comment> commOp = commentRepo.findById(commentid);

        if(commOp.isPresent()) {

            Comment comm = commOp.get();
            commentRepo.deleteById(commentid);
            return comm;
        }else {
            throw new Exception("Unable to find Comment with this id "+commentid);
        }

    }

    @Override
    public Comment updateComment(Integer commentid, Comment comment) throws Exception {
        Optional<Comment> commentTem = commentRepo.findById(commentid);
        if(commentTem.isPresent()) {
            Comment oldCom = commentTem.get();
            oldCom.setComment(comment.getComment());
            return commentRepo.save(oldCom);
        }else {
            throw new Exception("Comment doesn't exist with this id "+commentid);
        }
    }

    @Override
    public List<Comment> getAllComment(Integer postId) throws Exception {

        Optional<Post> postTem = postRepo.findById(postId);

        if(postTem.isPresent()) {

            Post post = postTem.get();
            List<Comment> comList = commentRepo.findAll();
            if(!comList.isEmpty()) {
                return comList;
            }
            else {
                throw new Exception("No Comment present");
            }
        }else {
            throw new Exception("Unable to find post with post_id"+postId);
        }

    }
}
