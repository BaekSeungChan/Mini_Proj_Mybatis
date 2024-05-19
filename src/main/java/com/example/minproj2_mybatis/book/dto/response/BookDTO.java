package com.example.minproj2_mybatis.book.dto.response;

import com.example.minproj2_mybatis.book.entity.BookEntity;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BookDTO {

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
    private String description;
    private int quantity;


    public static BookDTO toDTO(BookEntity book){
        return BookDTO.builder()
                .id(book.getId())
                .username(book.getUsername())
                .title(book.getTitle())
                .link(book.getLink())
                .image(book.getImage())
                .author(book.getAuthor())
                .discount(book.getDiscount())
                .publisher(book.getPublisher())
                .pubdate(book.getPubdate())
                .isbn(book.getIsbn())
                .quantity(book.getQuantity())
                .build();
    }
}
