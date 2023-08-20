package com.book.Post.command.application.controller;

import com.book.Post.command.application.dto.PostDTO;
import com.book.Post.command.application.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    public String list(Model model) {
        List<PostDTO> boardDtoList = postservice.getBoardList();
        model.addAttribute("postList", boardDtoList);
        return "list";
    }

    @GetMapping("/post")
    public String post() {
        return "post";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        PostDTO boardDto = postservice.getPost(id);
        model.addAttribute("post", boardDto);
        return "detail.html";
    }



    @PostMapping("/post")
    public String detailview(PostDTO postdto) {
        return "redirect:/";
    }




}
