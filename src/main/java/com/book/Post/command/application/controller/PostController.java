package com.book.Post.command.application.controller;

import com.book.Member.command.application.dto.MemberDTO;
import com.book.Member.command.application.service.RealLoginService;
import com.book.Member.command.domain.aggregate.entity.MemberEntity;
import com.book.Post.command.application.dto.PostCommentDTO;
import com.book.Post.command.application.dto.PostDTO;
import com.book.Post.command.application.dto.PostLikeDTO;
import com.book.Post.command.application.service.PostCommentService;
import com.book.Post.command.application.service.PostLikeService;
import com.book.Post.command.application.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/ebook")
public class PostController {
    private final PostService postservice;
    private final PostLikeService postlikeservice;
    private final PostCommentService postCommentService;
    private final RealLoginService realLoginService;


    public PostController(PostService postservice, PostLikeService postlikeservice, PostCommentService postCommentService, RealLoginService realLoginService) {
        this.postservice = postservice;
        this.postlikeservice = postlikeservice;
        this.postCommentService = postCommentService;
        this.realLoginService = realLoginService;
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
        return "redirect:/ebook/list";
    }

    @Transactional
    public void saveRegister(MemberDTO memberdto) {

        MemberEntity member = new MemberEntity();
        member.setMemberId(memberdto.getMemberId());
        member.setMemberNickname(memberdto.getMemberNickname());
        member.setMemberEmail(memberdto.getMemberEmail());
        member.setIsDeleted(memberdto.getIsDeleted());
        member.setPermission(memberdto.getPermission());

    }

    @PostMapping("/createposter/{id}")
    public String update(@PathVariable("id") Long id,@RequestParam String title, @RequestParam String content,HttpSession session) {
        String memberida = String.valueOf(session.getAttribute("memberid"));
        String nickname = (String)session.getAttribute("nickname");
        PostDTO postdto = new PostDTO();
        postdto.setId(id);
        postdto.setTitle(title);
        postdto.setContent(content);
        postdto.setMemberid(memberida);
        postdto.setAuthor(nickname);
        postdto.setIsdelete("0");
        System.out.println("memberida = " + memberida);
        postservice.saveeditPost(postdto,id);
        return "redirect:/ebook/list";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,HttpSession session) {
        String memberida = String.valueOf(session.getAttribute("memberid"));
        String nickname = (String)session.getAttribute("nickname");
        postservice.delete(id);
        return "redirect:/ebook/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<PostDTO> boardDtoList = postservice.getBoardList();
        model.addAttribute("postList", boardDtoList);
        return "index";
    }
    @CrossOrigin("*")
    @GetMapping("/imagelist")
    public String Imagelist(Model model) {
        List<PostDTO> boardDtoList = postservice.getImageBoardList();
        model.addAttribute("postList", boardDtoList);
        return "imagelist";
    }

    @GetMapping("/admin")
    public ModelAndView adminlist(ModelAndView model) {
        List<PostDTO> boardDtoList = postservice.getDeleteBoardList();
        //List<MemberDTO> boardDtoLister = realLoginService.AdminRegisterlist();
        model.addObject("postList", boardDtoList);
        //model.addObject("postLister", boardDtoLister);
        model.setViewName("admin");
        return model;
    }
    @GetMapping("/useradmin")
    public ModelAndView adminwritelist(ModelAndView model) {
        List<MemberDTO> boardDtoLister = realLoginService.AdminRegisterlist();
        model.addObject("postLister", boardDtoLister);
        model.setViewName("useradmin");
        return model;
    }


    @GetMapping("/post")
    public String post() {
        return "post";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        PostDTO boardDto = postservice.getPost(id);
        List<PostCommentDTO> boardDtoList = postCommentService.getPostComments(id);
        model.addAttribute("commentlist", boardDtoList);
        model.addAttribute("post", boardDto);
        model.addAttribute("postid",id);
        System.out.println(boardDtoList);
        return "detail";
    }
    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        PostDTO boardDto = postservice.getPost(id);
        model.addAttribute("post", boardDto);
        return "edit";
    }

   @PostMapping("/post")
    public String detailview(PostDTO postdto) {
        return "redirect:/ebook/list";
    }

    @PostMapping("/post1/{id}")
    public String insert(@PathVariable("id") Long id, PostLikeDTO postLikeDTO, Model model) throws Exception {
        PostDTO boardDto = postservice.getPost(id);
        model.addAttribute("post", boardDto);
        return "detail.html";
    }


}
