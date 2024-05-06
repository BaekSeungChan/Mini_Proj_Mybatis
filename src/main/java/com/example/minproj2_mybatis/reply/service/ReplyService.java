package com.example.minproj2_mybatis.reply.service;

import com.example.minproj2_mybatis.reply.dto.ReplyDTO;
import com.example.minproj2_mybatis.reply.entity.ReplyEntity;
import com.example.minproj2_mybatis.reply.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyMapper replyMapper;

    public void save(ReplyDTO replyDTO) {
        replyMapper.save(ReplyEntity.toEntity(replyDTO));
    }

    public List<ReplyDTO> findAll(Long boardId) {

        List<ReplyEntity> reply = replyMapper.findAll(boardId);

        for(var aa : reply){
            System.out.println("dkdkdk " + aa.toString());
        }



        List<ReplyDTO> replyDTOList =  reply.stream().map(ReplyDTO::toDTO)
                .collect(Collectors.toList());

        System.out.println(replyDTOList);


        return replyDTOList;
    }
}
