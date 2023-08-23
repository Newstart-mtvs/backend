package com.book.Post.command.application.controller;

import com.book.Post.command.application.service.PostLikeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/ebook")
public class PostLikeController {

    private final PostLikeService postlikeservice;

    public PostLikeController(PostLikeService postlikeservice) {
        this.postlikeservice = postlikeservice;
    }


}
