package com.book.Member.command.domain.aggregate.entity;

import com.book.Member.command.domain.aggregate.vo.Role;
import com.book.Post.command.domain.aggregate.entity.PostLikeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity(name = "Member")
@Table(name = "MEMBER")
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "SEQ_MEMBER_NUM",
        initialValue = 1,
        allocationSize = 50
)
public class MemberEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR"
    )
    @Column(name = "MEMBER_NUM")
    private int memberNum;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "MEMBER_NICKNAME", unique = true, nullable = false)
    private String memberNickname;

    @Column(name = "MEMBER_EMAIL", unique = true, nullable = false)
    private String memberEmail;

    @CreatedDate
    @Column(name = "JOIN_DATE", nullable = false)
    private String joinDate;

    @Column(name = "IS_DELETED", columnDefinition = "varchar (2)", nullable = false)
    private String isDeleted;

    @Column(name = "permission")
    @Enumerated(EnumType.STRING)
    private Role permission;

    public MemberEntity() {}

    public int getMemberNum() {
        return memberNum;
    }

    @PrePersist
    public void onPrePersist() {
        this.joinDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }



    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<PostLikeEntity> hearts;
}
