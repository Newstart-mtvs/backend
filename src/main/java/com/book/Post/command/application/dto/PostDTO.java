package com.book.Post.command.application.dto;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;
@Getter
@Setter
@RequiredArgsConstructor   // Bean 주입 : @Autowired 대신에 생성자로!
@ToString
@AllArgsConstructor
@Builder
public class PostDTO {
    private Long id;
    private String author;
    private String title;
    private String content;
    private String joinDate;
    private String publisher;
    private LocalDateTime modifiedDate;
    private String isdelete;


}