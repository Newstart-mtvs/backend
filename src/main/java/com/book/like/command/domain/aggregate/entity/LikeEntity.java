package com.book.like.command.domain.aggregate.entity;

import com.book.Member.command.domain.aggregate.entity.MemberEntity;
import com.book.Post.command.domain.aggregate.entity.PostEntitiy;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "LIKE_TBL")
@NoArgsConstructor
@ToString
public class LikeEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "LIKE_SEQ_GENERATOR"
    )
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY) // 왜 쓰는건지 확실하게 알고 쓸 것
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntitiy post;

    @CreatedDate
    @Column
    private LocalDate likedDate;

    public LikeEntity(Long memberId) {
        this.member = new MemberEntity(memberId);
    }

    @PrePersist
    public void onPrePersist() {
        this.likedDate = LocalDate.now();
    }
}
