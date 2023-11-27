package com.book.FileUpload.command.application.dto;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor   // Bean 주입 : @Autowired 대신에 생성자로!
@ToString
@AllArgsConstructor
@Builder
public class FileDTO {

    private Long id;
    private String orgNm;
    private String savedNm;
    private String savedPath;


}
