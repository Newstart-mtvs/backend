package com.book.Member.command.application.dto;

import com.book.Member.command.domain.aggregate.vo.Role;
import jdk.jshell.Snippet;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;

@Getter
@Setter
@RequiredArgsConstructor   // Bean 주입 : @Autowired 대신에 생성자로!
@ToString
@AllArgsConstructor
@Builder
public class MemberDTO {
    private int memberNum;
    private Long memberId;

    private String memberNickname;

    private String memberEmail;

    private String joinDate;

    private String isDeleted;
    private Role permission;


}
