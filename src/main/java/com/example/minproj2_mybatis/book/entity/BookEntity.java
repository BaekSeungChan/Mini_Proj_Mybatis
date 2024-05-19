package com.example.minproj2_mybatis.book.entity;


import com.example.minproj2_mybatis.book.dto.request.BookCartRequest;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {


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



    public static BookEntity toEntity(BookCartRequest request){
        return BookEntity.builder()
                .username(request.getUsername())
                .title(request.getTitle())
                .link(request.getLink())
                .image(request.getImage())
                .author(request.getAuthor())
                .discount(request.getDiscount())
                .publisher(request.getPublisher())
                .pubdate(request.getPubdate())
                .isbn(request.getIsbn())
                .quantity(request.getQuantity())
                .build();
    }

}
