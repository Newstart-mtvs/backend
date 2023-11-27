package com.book.Member.command.application.service;

import com.book.Member.command.application.dto.MemberDTO;
import com.book.Member.command.domain.aggregate.entity.MemberEntity;
import com.book.Member.command.domain.repository.LoginRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RealLoginService {
    private final LoginRepository loginRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RealLoginService(LoginRepository loginRepository, ModelMapper modelMapper) {
        this.loginRepository = loginRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void saveRegister(MemberDTO memberdto) {

        MemberEntity member = new MemberEntity();
        member.setMemberId(memberdto.getMemberId());
        member.setMemberNickname(memberdto.getMemberNickname());
        member.setMemberEmail(memberdto.getMemberEmail());
        member.setIsDeleted(memberdto.getIsDeleted());
        member.setPermission(memberdto.getPermission());
        loginRepository.save(member);
        loginRepository.flush();

    }

    @Transactional
    public boolean duplicate(String reportNo) {
        return loginRepository.existsByMemberEmail(reportNo);

    }

    @Transactional
    public List<MemberDTO> AdminRegisterlist() {


        List<MemberEntity> reportlist = loginRepository.findAll();

        List<MemberDTO> PostDTOList = new ArrayList<>();
        for (MemberEntity board : reportlist) {
            MemberDTO filer = MemberDTO.builder()
                    .memberNum(board.getMemberNum())
                    .memberNickname(board.getMemberNickname())
                    .memberEmail(board.getMemberEmail())
                    .build();
            PostDTOList.add(filer);
        }
        return PostDTOList;

    }


}
