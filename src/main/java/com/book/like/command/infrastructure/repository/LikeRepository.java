package com.book.like.command.infrastructure.repository;

import com.book.Member.command.domain.aggregate.entity.MemberEntity;
import com.book.Post.command.domain.aggregate.entity.PostEntitiy;
import com.book.like.command.domain.aggregate.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    void deleteByMemberAndPost(MemberEntity member, PostEntitiy post);
}
