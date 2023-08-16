package com.book.Post.command.domain.repository;

import com.book.Member.command.domain.aggregate.entity.MemberEntity;
import com.book.Post.command.domain.aggregate.entity.PostEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntitiy, String> {
}
