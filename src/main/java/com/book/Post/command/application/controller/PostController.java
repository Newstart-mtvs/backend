package com.book.Post.command.application.controller;

import com.book.Post.command.application.dto.PostDTO;
import com.book.Post.command.application.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/ebook")
public class PostController {
    private final PostService postservice;

    public PostController(PostService postservice) {
        this.postservice = postservice;
    }

    @PostMapping("createpost")
    public String write(Model model, @RequestParam String title, @RequestParam String content, HttpSession session) {
        PostDTO postdto = new PostDTO();
        String nickname = (String)session.getAttribute("nickname");
        postdto.setTitle(title);
        postdto.setContent(content);
        postdto.setAuthor(nickname);
        postdto.setIsdelete("0");
        postservice.savePost(postdto);
        return "작성이 완료 되었습니담";
    }

    @GetMapping("/list")
    public String list() {
        return "board/list.html";
    }

    @GetMapping("/post")
    public String post() {
        return "post";
    }


    @PostMapping("/post")
    public String detailview(PostDTO postdto) {
        return "redirect:/";
    }




}
