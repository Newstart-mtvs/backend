package com.book.Post.command.application.controller;

import com.book.Post.command.application.dto.PostDTO;
import com.book.Post.command.application.dto.PostLikeDTO;
import com.book.Post.command.application.service.PostLikeService;
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
    private final PostLikeService postlikeservice;

    public PostController(PostService postservice, PostLikeService postlikeservice) {
        this.postservice = postservice;
        this.postlikeservice = postlikeservice;
    }

    @PostMapping("createpost")
    public String write(Model model, @RequestParam String title, @RequestParam String content, HttpSession session) {
        PostDTO postdto = new PostDTO();
        String nickname = (String)session.getAttribute("nickname");
        String memberida = String.valueOf(session.getAttribute("memberid"));
        System.out.println("memberid = " + memberida);
        System.out.println("nickname = " + nickname);
        postdto.setTitle(title);
        postdto.setContent(content);
        postdto.setAuthor(nickname);
        postdto.setMemberid(memberida);
        postdto.setIsdelete("0");
        postservice.savePost(postdto);
        return "작성이 완료 되었습니담";
    }
    @PostMapping("/createposter/{id}")
    public String update(@PathVariable("id") Long id,@RequestParam String title, @RequestParam String content,HttpSession session) {
        String memberida = String.valueOf(session.getAttribute("memberid"));
        String nickname = (String)session.getAttribute("nickname");
        PostDTO postdto = new PostDTO();
        postdto.setId(id);
        postdto.setTitle(title);
        postdto.setContent(content);
        postdto.setMemberid("65556565");
        postdto.setAuthor("nickname");
        postdto.setIsdelete("0");
        System.out.println("memberida = " + memberida);
        postservice.saveeditPost(postdto,id);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,HttpSession session) {
        String memberida = String.valueOf(session.getAttribute("memberid"));
        String nickname = (String)session.getAttribute("nickname");
        postservice.delete(id);
        return "redirect:/";
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
    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        PostDTO boardDto = postservice.getPost(id);
        model.addAttribute("post", boardDto);
        return "edit.html";
    }

   @PostMapping("/post")
    public String detailview(PostDTO postdto) {
        return "redirect:/";
    }

    @PostMapping("/post1/{id}")
    public String insert(@PathVariable("id") Long id, PostLikeDTO postLikeDTO, Model model) throws Exception {
        PostDTO boardDto = postservice.getPost(id);
        model.addAttribute("post", boardDto);
        postLikeDTO.setMemberidpost(id);
        postlikeservice.insert(postLikeDTO);
        return "detail.html";
    }


}
