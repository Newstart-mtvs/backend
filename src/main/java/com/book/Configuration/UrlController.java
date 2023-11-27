package com.book.Configuration;

import com.book.Post.command.application.dto.PostDTO;
import com.book.Post.command.application.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UrlController {

    private final PostService postService;

    public UrlController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("index1")
    public ModelAndView josa(ModelAndView model, HttpSession session){
        System.out.println("index1");
        String memberida = String.valueOf(session.getAttribute("memberid"));
        System.out.println("memberid = " + memberida);
        model.addObject("sessiona", memberida);
        model.setViewName("index1");
        return model;
    }

    @GetMapping("board")
    public ModelAndView board(ModelAndView model, HttpSession session){
        System.out.println("board");
        String memberida = String.valueOf(session.getAttribute("memberid"));
        System.out.println("memberid = " + memberida);
        List<PostDTO> boardDtoList = postService.getBoardList();
        model.addObject("postList", boardDtoList);
        model.addObject("sessiona", memberida);

        model.setViewName("board");
        return model;
    }

    @GetMapping("imageboard")
    public ModelAndView imageboard(ModelAndView model, HttpSession session){
        System.out.println("imageboard");
        String memberida = String.valueOf(session.getAttribute("memberid"));
        System.out.println("memberid = " + memberida);
        List<PostDTO> boardDtoList = postService.getImageBoardList();
        model.addObject("postList", boardDtoList);
        model.addObject("sessiona", memberida);
        model.setViewName("imageboard");
        return model;
    }


}
