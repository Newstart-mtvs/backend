package com.book.Member.command.domain.repository;

import com.book.Member.command.domain.aggregate.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<MemberEntity, Integer> {
    boolean existsByMemberEmail(String name);
    MemberEntity findByMemberId(long name);
    MemberEntity findByMemberNum(int id);

}
