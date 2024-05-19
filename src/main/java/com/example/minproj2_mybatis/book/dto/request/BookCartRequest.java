package com.example.minproj2_mybatis.book.dto.request;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookCartRequest {

    private Long id;
    private String username;
    private String title;
    private String link;
    private String image;
    private String author;
    private String discount;
    private String publisher;
    private String pubdate;
    private String isbn;
    private int quantity;
}
