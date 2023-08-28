package com.book.Post.command.application.controller;

import com.book.Post.command.application.dto.PostLikeDTO;
import com.book.Post.command.application.service.PostLikeService;
import com.book.Post.command.domain.aggregate.entity.PostLikeEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/ebook")
public class PostLikeController {

    private final PostLikeService postlikeservice;

    public PostLikeController(PostLikeService postlikeservice) {
        this.postlikeservice = postlikeservice;
    }

    @GetMapping("/like/{id}")
    public String heart(@PathVariable("id") Long id,HttpSession session) throws IOException {
        String memberida = String.valueOf(session.getAttribute("memberid"));
        PostLikeDTO heartDto = new PostLikeDTO();
        heartDto.setCampaignId(String.valueOf(id));
        System.out.println("Long.parseLong(memberida) = " + Long.parseLong(memberida));
        heartDto.setUserId(Long.parseLong(memberida));
        postlikeservice.heart(heartDto);
        return "redirect:/lister";
    }

}
