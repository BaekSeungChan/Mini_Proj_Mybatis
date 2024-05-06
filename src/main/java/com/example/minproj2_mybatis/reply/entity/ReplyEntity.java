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
@ToString
public class ReplyEntity {

    private Long id;

    private String replyWriter;

    private String replyContents;

    private Long boardId;

    private Timestamp replyCreatedTime;

    public static ReplyEntity toEntity(ReplyDTO replyDTO){
        return ReplyEntity.builder()
                .boardId(replyDTO.getBoardId())
                .replyWriter(replyDTO.getWriter())
                .replyContents(replyDTO.getContent())
                .build();

    }

}
