package com.book.Post.command.application.service;

import com.book.Post.command.application.dto.PostDTO;
import com.book.Post.command.domain.aggregate.entity.PostEntitiy;
import com.book.Post.command.domain.repository.PostRepository;
import org.springframework.data.domain.Sort;
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
        posts.setMemberId(postdto.getMemberid());
        posts.setIsDeleted(postdto.getIsdelete());
        postRepository.save(posts);
        postRepository.flush();
    }

    @Transactional
    public void saveeditPost(PostDTO postdto,Long id) {
        PostEntitiy posts = postRepository.findById(id).get();
        posts.setTitle(postdto.getTitle());
        posts.setContent(postdto.getContent());
        posts.setAuthor(postdto.getAuthor());
        posts.setMemberId(postdto.getMemberid());
        posts.setIsDeleted(postdto.getIsdelete());
    }

    @Transactional
    public void delete(Long id) {
        PostEntitiy posts = postRepository.findById(id).get();
        posts.setIsDeleted("1");
    }

    @Transactional
    public List<PostDTO> getBoardList() {
        List<PostEntitiy> PostList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
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
            if(Integer.parseInt(board.getIsDeleted()) == 0) {
                PostDTOList.add(boardDto);
            }
        }
        return PostDTOList;
    }

    @Transactional
    public PostDTO getPost(Long id) {
        PostEntitiy board = postRepository.findById(id).get();

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
        return boardDto;
    }


}
