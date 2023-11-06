package com.book.FileUpload.command.domain.aggregate.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Download")
@Table(name = "Download")
@Builder
public class DownloadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    private String orgNm;

    private String savedNm;

    private String savedPath;

}