package com.example.minproj2_mybatis.board.controller;

import com.example.minproj2_mybatis.board.dto.BoardDTO;
import com.example.minproj2_mybatis.board.entity.BoardEntity;
import com.example.minproj2_mybatis.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model){
        List<BoardDTO> board = boardService.findAll();

        model.addAttribute("list", board);

        return "board/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id, Model model){

        BoardDTO boardDTO = boardService.findById(id);

        model.addAttribute("board", boardDTO);

        return "board/detail";
    }

    @GetMapping("/write")
    public String write(){
        return "board/write";
    }

    @PostMapping("/write")
    public String write(
            @RequestParam("title") String title,
            @RequestParam("writer") String writer,
            @RequestParam("content") String content,
            @RequestParam("password") String password
    ){

        BoardDTO boardDTO = BoardDTO.builder()
                .title(title)
                .writer(writer)
                .password(password)
                .content(content)
                .build();


        int result = boardService.save(boardDTO);

        if(result > 0){
            return "redirect:/board/list";
        } else {
            return "/write";
        }
    }


    @GetMapping("/edit")
    public String edit(@RequestParam("id") Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);

        model.addAttribute("board", boardDTO);

        return "/board/edit";
    }

    @PostMapping("/edit")
    public String edit(
            @RequestParam("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("writer") String writer,
            @RequestParam("content") String content
            ){

        BoardDTO boardDTO = BoardDTO.builder()
                .id(id)
                .title(title)
                .writer(writer)
                .content(content)
                .build();

        int result = boardService.updateBoard(boardDTO);

        if(result > 0){
            return "redirect:/board/list";
        } else {
            return "/write";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id){
        int result = boardService.deleteBoard(id);

        if(result > 0){
            return "redirect:/board/list";
        } else {
            return "/write";
        }
    }
}
