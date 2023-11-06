package com.book.Post.command.application.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@RequiredArgsConstructor   // Bean 주입 : @Autowired 대신에 생성자로!
@ToString
@AllArgsConstructor
@Builder
public class PostCommentDTO {
    private Long id;
    private String comment;
    private String createdDate;
    private String modifiedDate;
    private Long postsId;
    private String userid;

}
