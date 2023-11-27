package com.book.Post.command.application.service;

import com.book.Post.command.application.dto.PostCommentDTO;
import com.book.Post.command.application.dto.PostDTO;
import com.book.Post.command.domain.aggregate.entity.PostCommentEntity;
import com.book.Post.command.domain.aggregate.entity.PostEntitiy;
import com.book.Post.command.domain.repository.PostCommentRepository;
import com.book.Post.command.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostCommentService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostCommentRepository commentRepository;

    @Transactional
    public List<PostCommentDTO> getPostComments(Long id) {
        List<PostCommentEntity> commentList = commentRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        List<PostCommentDTO> commentDTOList = new ArrayList<>();
        for (PostCommentEntity board : commentList) {
            PostCommentDTO boardDto = PostCommentDTO.builder()
                    .id(board.getId())
                    .comment(board.getComment())
                    .postsId(board.getPosts().getId())
                    .userid(board.getUserid())
                    .createdDate(board.getCreatedDate())
                    .build();

            if (board.getPosts().getId() == id) {
                commentDTOList.add(boardDto);
            }
        }
        return commentDTOList;
    }

    @Transactional
    public PostCommentDTO PostComments(PostCommentDTO postCommentDTO) {
        PostCommentEntity newComment = new PostCommentEntity();
        newComment.setUserid(postCommentDTO.getUserid());
        newComment.setPosts(postRepository.getReferenceById(Long.valueOf(postCommentDTO.getPostsId())));
        newComment.setComment(postCommentDTO.getComment());
        commentRepository.save(newComment);
        return null;
    }

    @Transactional
    public void saveeditPost(PostCommentDTO postCommentDTO, Long id) {
        PostCommentEntity posts = commentRepository.findById(id).get();
        posts.setUserid(postCommentDTO.getUserid());
        posts.setComment(postCommentDTO.getComment());
    }

    @Transactional
    public void deletecommentPost(Long id) {
        PostCommentEntity posts = commentRepository.findById(id).get();
        commentRepository.deleteById(id);
    }
}
