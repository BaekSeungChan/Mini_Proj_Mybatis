package com.example.minproj2_mybatis.board.controller;

import com.example.minproj2_mybatis.board.dto.BoardDTO;
import com.example.minproj2_mybatis.board.entity.BoardEntity;
import com.example.minproj2_mybatis.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(){

        return "board/list";
    }

    @GetMapping("/write")
    public String write(){
        return "board/write";
    }

    @PostMapping("/write")
    public String write(
            @RequestParam("title") String title,
            @RequestParam("writer") String writer,
            @RequestParam("content") String content
    ){

        BoardDTO boardDTO = BoardDTO.builder()
                .title(title)
                .writer(writer)
                .content(content)
                .build();


        int result = boardService.save(boardDTO);

        if(result > 0){
            return "redirect:/board/list";
        } else {
            return "/write";
        }
    }
}
