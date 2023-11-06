package com.book.Post.command.application.controller;

import com.book.Post.command.application.dto.PostCommentDTO;
import com.book.Post.command.application.dto.PostDTO;
import com.book.Post.command.application.service.PostCommentService;
import com.book.Post.command.domain.aggregate.entity.PostCommentEntity;
import com.book.Post.command.domain.repository.PostCommentRepository;
import com.book.Post.command.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class PostCommentController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostCommentRepository commentRepository;

    private final PostCommentService postCommentService;

    public PostCommentController(PostCommentService postCommentService) {
        this.postCommentService = postCommentService;
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/post/{id}/comment/{commentID}")
    public ModelAndView updateComment(@PathVariable Long id,@PathVariable Long commentID, ModelAndView mav,HttpSession session,@RequestBody PostCommentDTO comment){
        String memberida = String.valueOf(session.getAttribute("memberid"));
        PostCommentDTO postcomment = new PostCommentDTO();
        postcomment.setPostsId(id);
        postcomment.setUserid(memberida);
        postcomment.setComment(comment.getComment());
        postCommentService.saveeditPost(postcomment,id);
        mav.setViewName("detail");
        mav.addObject("postid", id);
        return mav;

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/post/{id}/comment")
    public ModelAndView createComment(@PathVariable Long id, HttpSession session, @RequestBody PostCommentDTO comment){
        String memberida = String.valueOf(session.getAttribute("memberid"));
        PostCommentDTO postcomment = new PostCommentDTO();
        postcomment.setPostsId(id);
        postcomment.setUserid(memberida);
        postcomment.setComment(comment.getComment());
        postCommentService.PostComments(postcomment);
        return null;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/post/{id}/comment/{commentID}")
    public String deleteComment(@PathVariable Long id, @PathVariable Long commentID){
        commentRepository.deleteById(commentID);
        return "Comment Delete Success!";
    }
}
