package com.book.Post.command.domain.aggregate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity(name = "POST")
@Table(name = "POST")
@SequenceGenerator(
        name = "POST_SEQ_GENERATOR",
        sequenceName = "SEQ_POST_NUM",
        initialValue = 1,
        allocationSize = 50
)
public class PostEntitiy {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "POST_SEQ_GENERATOR"
    )
    private Long id;

    @Column(length = 30, nullable = false)
    private String author;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @CreatedDate
    @Column(name = "JOIN_DATE", nullable = false)
    private String joinDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public PostEntitiy() {

    }


    @PrePersist
    public void onPrePersist() {
        this.joinDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


}
