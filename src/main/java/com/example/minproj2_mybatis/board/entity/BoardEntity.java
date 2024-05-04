package com.example.minproj2_mybatis.board.entity;

import com.example.minproj2_mybatis.board.dto.BoardDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardEntity {

    private Long id;

    private String title;

    private String content;

    private String writer;

    private String password;

    private int readCount;

    private LocalDateTime writeTime;

    private LocalDateTime editTime;


    public static BoardEntity toEntity(BoardDTO boardDTO){
        return BoardEntity.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(boardDTO.getWriter())
                .password(boardDTO.getPassword())
                .readCount(boardDTO.getReadCount())
                .build();
    }

}
