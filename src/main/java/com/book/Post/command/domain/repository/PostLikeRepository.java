package com.book.Post.command.domain.repository;

import com.book.Member.command.domain.aggregate.entity.MemberEntity;
import com.book.Post.command.domain.aggregate.entity.PostEntitiy;
import com.book.Post.command.domain.aggregate.entity.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikeEntity, Long> {
}
