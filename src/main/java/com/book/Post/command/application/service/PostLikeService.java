package com.book.Post.command.application.service;

import com.book.Member.command.domain.aggregate.entity.MemberEntity;
import com.book.Member.command.domain.repository.LoginRepository;
import com.book.Post.command.application.dto.PostDTO;
import com.book.Post.command.application.dto.PostLikeDTO;
import com.book.Post.command.domain.aggregate.entity.PostEntitiy;
import com.book.Post.command.domain.aggregate.entity.PostLikeEntity;
import com.book.Post.command.domain.repository.PostLikeRepository;
import com.book.Post.command.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostLikeService {


}
