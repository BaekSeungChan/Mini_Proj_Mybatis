package com.example.minproj2_mybatis.reply.controller;

import com.example.minproj2_mybatis.reply.dto.ReplyDTO;
import com.example.minproj2_mybatis.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/save")
    public @ResponseBody List<ReplyDTO> save(@RequestBody ReplyDTO replyDTO){
        System.out.println("relry " + replyDTO);

        replyService.save(replyDTO);

        List<ReplyDTO> replyDTOList = replyService.findAll(replyDTO.getBoardId());

        return replyDTOList;
    }

}
