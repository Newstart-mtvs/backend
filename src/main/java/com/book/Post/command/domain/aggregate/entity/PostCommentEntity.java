package com.book.Post.command.domain.aggregate.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity(name = "Postcomment")
@Table(name = "Postcomment")
@Builder
public class PostCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment; // 댓글 내용

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntitiy posts;

    @Column(length = 130)
    private String userid;


    public PostCommentEntity() {

    }
}

