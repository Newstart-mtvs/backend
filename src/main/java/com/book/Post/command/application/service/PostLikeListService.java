package com.book.Post.command.application.service;

import com.book.Member.command.domain.aggregate.entity.MemberEntity;
import com.book.Member.command.domain.repository.LoginRepository;
import com.book.Member.command.infrastructure.repository.MemberRepository;
import com.book.Post.command.application.dto.PostDTO;
import com.book.Post.command.application.dto.PostLikeDTO;
import com.book.Post.command.domain.aggregate.entity.PostEntitiy;
import com.book.Post.command.domain.aggregate.entity.PostLikeEntity;
import com.book.Post.command.domain.repository.PostLikeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostLikeListService {
    private LoginRepository loginRepository;
    private PostLikeRepository postLikeRepository;



    public PostLikeListService(LoginRepository loginRepository, PostLikeRepository postLikeRepository) {
        this.loginRepository = loginRepository;
        this.postLikeRepository = postLikeRepository;
    }

    @Transactional
    public List<PostLikeDTO> getBoardList(Long memberid) {
        List<PostLikeEntity> PostList = postLikeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<PostLikeDTO> PostDTOList = new ArrayList<>();
        int member = loginRepository.findByMemberId(memberid).getMemberNum();

        for(PostLikeEntity board : PostList) {
            PostLikeDTO boardDto = PostLikeDTO.builder()
                    .id(board.getId())
                    .userId(board.getUser().getMemberNum())
                    .campaignId(board.getCampaignId())
                    .title(board.getTitle())
                    .joinDate(board.getJoinDate())
                    .build();
            if(board.getUser().getMemberNum() == member) {
                PostDTOList.add(boardDto);
                System.out.println("board.getUser().getMemberId() = " + board.getUser().getMemberNum());
                System.out.println("memberid = " + memberid);
                }
        }
        return PostDTOList;
    }
}
