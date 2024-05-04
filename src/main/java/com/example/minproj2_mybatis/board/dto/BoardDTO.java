package com.example.minproj2_mybatis.board.dto;

import com.example.minproj2_mybatis.board.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDTO {

    private Long id;

    private String title;

    private String content;

    private String writer;

    private String password;

    private int readCount;

    private LocalDateTime writeTime;

    private LocalDateTime editTime;


    public static BoardDTO toDTO(BoardEntity boardEntity){
        return BoardDTO.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .writer(boardEntity.getWriter())
                .content(boardEntity.getContent())
                .readCount(boardEntity.getReadCount())
                .writeTime(boardEntity.getWriteTime())
                .editTime(boardEntity.getEditTime())
                .build();
    }

}
