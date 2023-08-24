package com.book.Post.command.domain.aggregate.entity;

import com.book.Member.command.domain.aggregate.entity.MemberEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity(name = "POSTLIKE")
@Table(name = "POSTLIKE")
@SequenceGenerator(
        name = "POST_SEQLIKE_GENERATOR",
        sequenceName = "SEQ_POSTLIKE_NUM",
        initialValue = 1,
        allocationSize = 50
)
@Builder

public class PostLikeEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "POST_SEQLIKE_GENERATOR"
    )
    private Long id;

    @Column(name = "campaign_id")
    @NonNull
    private String campaignId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private MemberEntity user;

    private String title;

    @CreatedDate
    @Column(name = "JOIN_DATE", nullable = false)
    private String joinDate;

    public PostLikeEntity() {

    }


    @PrePersist
    public void onPrePersist() {
        this.joinDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


}
