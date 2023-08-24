package com.book.Post.command.application.controller;

import com.book.Post.command.application.dto.PostDTO;
import com.book.Post.command.application.dto.PostLikeDTO;
import com.book.Post.command.application.service.PostLikeListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PostLikeListContrller {
    private final PostLikeListService postLikeListService;

    public PostLikeListContrller(PostLikeListService postLikeListService) {
        this.postLikeListService = postLikeListService;
    }


    @GetMapping("/lister")
    public String list(Model model, HttpSession session) {
        String memberida = String.valueOf(session.getAttribute("memberid"));
        System.out.println("memberida = " + memberida);
        List<PostLikeDTO> boardDtoList = postLikeListService.getBoardList(Long.valueOf(memberida));
        //model.addAttribute("postList", boardDtoList);
        System.out.println("boardDtoList = " + boardDtoList);
        return "list";
    }

}
