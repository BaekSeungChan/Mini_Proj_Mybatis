package com.example.minproj2_mybatis.reply.service;

import com.example.minproj2_mybatis.reply.dto.ReplyDTO;
import com.example.minproj2_mybatis.reply.entity.ReplyEntity;
import com.example.minproj2_mybatis.reply.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyMapper replyMapper;

    public void save(ReplyDTO replyDTO) {
        replyMapper.save(ReplyEntity.toEntity(replyDTO));
    }
}
