package com.example.minproj2_mybatis.admin;


import com.example.minproj2_mybatis.member.entity.MemberEntity;
import com.example.minproj2_mybatis.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final MemberMapper memberMapper;

//    public List<MemberDTO> findAll() {
//        List<MemberEntity> list = memberMapper.findAll();
//
//        List<MemberDTO> memberList = list.stream().map(member -> MemberDTO.toDTO(member)).collect(Collectors.toList());
//
//        return memberList;
//
//    }

    public void delete(Long id){
        memberMapper.delete(id);
    }
}
