package com.example.minproj2_mybatis.book.mapper;

import com.example.minproj2_mybatis.book.entity.BookEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookCartMapper {

    int save(BookEntity book);

    List<BookEntity> findByName(String name);
}
