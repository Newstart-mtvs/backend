package com.book.Post.command.application.dto;

import com.book.Member.command.domain.aggregate.entity.MemberEntity;
import com.book.Post.command.domain.aggregate.entity.PostEntitiy;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@RequiredArgsConstructor   // Bean 주입 : @Autowired 대신에 생성자로!
@ToString
@AllArgsConstructor
@Builder
public class PostLikeDTO {
    private Long id;

    private String memberid;

    private Long memberidpost;

    private String joinDate;
}
