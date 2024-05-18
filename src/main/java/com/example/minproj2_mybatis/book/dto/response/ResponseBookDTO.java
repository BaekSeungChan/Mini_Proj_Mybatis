package com.example.minproj2_mybatis.book.dto.response;

import lombok.*;

import java.util.List;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResponseBookDTO {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<BookDTO> items;
}
