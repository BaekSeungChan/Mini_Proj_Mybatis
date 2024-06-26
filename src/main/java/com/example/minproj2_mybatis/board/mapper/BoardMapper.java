package com.example.minproj2_mybatis.board.mapper;

import com.example.minproj2_mybatis.board.dto.BoardDTO;
import com.example.minproj2_mybatis.board.entity.BoardEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    int save(BoardEntity boardEntity);

    List<BoardEntity> findAll();

    BoardEntity findById(Long id);

    int updateById(BoardEntity boardEntity);

    int deleteById(Long id);

    void plusCount(Long id);

    List<BoardEntity> searchFindAll(Map<String, Object> searchParams);

    int boardCount();
}
