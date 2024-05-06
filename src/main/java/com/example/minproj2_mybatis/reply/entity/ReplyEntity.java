package com.example.minproj2_mybatis.reply.entity;

import com.example.minproj2_mybatis.reply.dto.ReplyDTO;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyEntity {

    private Long id;

    private String writer;

    private String content;

    private Long boardId;

    private Timestamp writeTime;

    public static ReplyEntity toEntity(ReplyDTO replyDTO){
        return ReplyEntity.builder()
                .boardId(replyDTO.getBoardId())
                .writer(replyDTO.getWriter())
                .content(replyDTO.getContent())
                .build();

    }

}
