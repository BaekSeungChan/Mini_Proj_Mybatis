package com.example.minproj2_mybatis.reply.dto;

import com.example.minproj2_mybatis.board.entity.BoardEntity;
import com.example.minproj2_mybatis.reply.entity.ReplyEntity;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
    private Long id;

    private String writer;

    private String content;

    private Long boardId;

    private Timestamp writeTime;

    public static ReplyDTO toDTO(ReplyEntity replyEntity){
        return ReplyDTO.builder()
                .writer(replyEntity.getReplyWriter())
                .content(replyEntity.getReplyContents())
                .boardId(replyEntity.getBoardId())
                .writeTime(replyEntity.getReplyCreatedTime())
                .build();
    }
}
