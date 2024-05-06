package com.example.minproj2_mybatis.reply.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReplyDTO {
    private Long id;

    private String writer;

    private String content;

    private Long boardId;

    private Timestamp writeTime;
}
