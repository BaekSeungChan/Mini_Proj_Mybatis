package com.example.minproj2_mybatis.board.service;

import com.example.minproj2_mybatis.board.dto.BoardDTO;
import com.example.minproj2_mybatis.board.entity.BoardEntity;
import com.example.minproj2_mybatis.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public int save(BoardDTO boardDTO){
        return boardMapper.save(BoardEntity.toEntity(boardDTO));
    }

    public List<BoardDTO> findAll(){
        List<BoardEntity> boardEntities = boardMapper.findAll();

        return boardEntities.stream().map(BoardDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public BoardDTO findById(Long id) {
        BoardEntity boardEntity = boardMapper.findById(id);

        if (boardEntity != null) {
            incrementReadCount(id);

            return BoardDTO.toDTO(boardEntity);
        } else {
            return null;
        }
    }

    @Transactional
    public void incrementReadCount(Long id){
        boardMapper.plusCount(id);
    }


    public int updateBoard(BoardDTO boardDTO) {
        BoardEntity boardEntity = boardMapper.findById(boardDTO.getId());

        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setWriter(boardDTO.getWriter());
        boardEntity.setContent(boardDTO.getContent());

        return boardMapper.updateById(boardEntity);
    }


    public int deleteBoard(Long id){
        return boardMapper.deleteById(id);
    }

}
