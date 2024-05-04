package com.example.minproj2_mybatis.board.dto;

import lombok.*;

import java.time.LocalDateTime;
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDTO {

    private Long id;

    private String title;

    private String content;

    private String writer;

    @Builder.Default
    private String password = "1234";

    @Builder.Default
    private int readCount = 1;

    private LocalDateTime writeTime;

    private LocalDateTime editTime;

}
