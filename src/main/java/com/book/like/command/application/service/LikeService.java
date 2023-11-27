package com.book.like.command.application.service;

import com.book.Member.command.domain.repository.LoginRepository;
import com.book.like.command.domain.aggregate.entity.LikeEntity;
import com.book.like.command.infrastructure.repository.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

//    private LoginRepository loginRepository; // 어플리케이션 단에서 일어나는 모든 작업들은 서비스 단에서 이루어져야 한다.

    private LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public void toggleLike(Long memberId, Long postId, boolean ifLiked) {
        // 로그인 검증 로직

        if(!ifLiked){
            LikeEntity likeEntity = new LikeEntity(memberId);

            likeRepository.save(likeEntity); // 생성된 정보 그대로 돌려주기 위해 근데 좋아요는 사실 돌려줄 필요 없음
        } else {
           // likeRepository.deleteByMemberIdAndPostId();
        }

    }
}
