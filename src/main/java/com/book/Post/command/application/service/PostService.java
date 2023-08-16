package com.book.Post.command.application.service;

import com.book.Post.command.application.dto.PostDTO;
import com.book.Post.command.domain.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }




}
