package com.example.minproj2_mybatis.board.service;

import com.example.minproj2_mybatis.board.dto.BoardDTO;
import com.example.minproj2_mybatis.board.dto.PageDTO;
import com.example.minproj2_mybatis.board.entity.BoardEntity;
import com.example.minproj2_mybatis.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


    int pageLimit = 10; // 한 페이지당 보여줄 글 갯수
    int blockLimit = 5; // 하단에 보여줄 페이지 번호 갯수

    public List<BoardDTO> searchFind(BoardDTO boardDTO, int page) {
        int pageStart = (page - 1) * pageLimit;

        Map<String, Object> searchParams = new HashMap<>();

        BoardEntity boardEntity = BoardEntity.toEntity(boardDTO);


        searchParams.put("boardEntity", boardEntity);
        searchParams.put("start", pageStart);
        searchParams.put("limit", pageLimit);

        List<BoardEntity> boardEntities = boardMapper.searchFindAll(searchParams);

        return boardEntities.stream()
                .map(board -> BoardDTO.toDTO(board))
                .collect(Collectors.toList());
    }

    public PageDTO pagingParam(int page) {
        int boardCount = boardMapper.boardCount();
        int maxPage = (int) Math.ceil((double) boardCount / pageLimit);
        int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        return pageDTO;
    }
}
