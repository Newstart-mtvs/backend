package com.book.Post.command.application.dto;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class PostDTO {
    private Long id;
    private String author;
    private String title;
    private String content;
    private String joinDate;
    private LocalDateTime modifiedDate;
}
