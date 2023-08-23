package com.book.Post.command.application.service;

import com.book.Member.command.domain.aggregate.entity.MemberEntity;
import com.book.Member.command.domain.repository.LoginRepository;
import com.book.Post.command.application.dto.PostLikeDTO;
import com.book.Post.command.domain.aggregate.entity.PostLikeEntity;
import com.book.Post.command.domain.repository.PostLikeRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PostLikeService {
    private MemberEntity user;
    private LoginRepository loginRepository;
    private PostLikeRepository postLikeRepository;

    public PostLikeService(LoginRepository loginRepository, PostLikeRepository postLikeRepository) {
        this.loginRepository = loginRepository;
        this.postLikeRepository = postLikeRepository;
    }

    public String heart(PostLikeDTO heartDto) throws IOException {

        // 이미 좋아요 된 캠페인일 경우 409 에러

        if (postLikeRepository.existsByUserAndCampaignId(loginRepository.findByMemberId(heartDto.getUserId()), heartDto.getCampaignId())){
        return "exist";
        }
        PostLikeEntity heart = PostLikeEntity.builder()
                .campaignId(heartDto.getCampaignId())
                .user(loginRepository.findByMemberId(heartDto.getUserId()))
                .build();
        postLikeRepository.save(heart);
        return "qwrwrqrrqw";


    }

}