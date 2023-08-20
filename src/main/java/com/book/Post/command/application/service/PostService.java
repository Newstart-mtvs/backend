package com.book.Post.command.application.service;

import com.book.Member.command.domain.aggregate.entity.MemberEntity;
import com.book.Post.command.application.dto.PostDTO;
import com.book.Post.command.domain.aggregate.entity.PostEntitiy;
import com.book.Post.command.domain.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public void savePost(PostDTO postdto) {
        PostEntitiy posts = new PostEntitiy();
        posts.setTitle(postdto.getTitle());
        posts.setContent(postdto.getContent());
        posts.setAuthor(postdto.getAuthor());
        posts.setIsDeleted(postdto.getIsdelete());
        postRepository.save(posts);
        postRepository.flush();
    }

    @Transactional
    public List<PostDTO> getBoardList() {
        List<PostEntitiy> PostList = postRepository.findAll();
        List<PostDTO> PostDTOList = new ArrayList<>();
        for(PostEntitiy board : PostList) {
            PostDTO boardDto = PostDTO.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .publisher(board.getPublisher())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .joinDate(board.getJoinDate())
                    .modifiedDate(board.getModifiedDate())
                    .isdelete(board.getIsDeleted())
                    .build();
            PostDTOList.add(boardDto);
        }
        return PostDTOList;
    }


}
