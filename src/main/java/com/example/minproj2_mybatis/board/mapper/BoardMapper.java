package com.example.minproj2_mybatis.board.mapper;

import com.example.minproj2_mybatis.board.entity.BoardEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    int save(BoardEntity boardEntity);
}
