package com.example.minproj2_mybatis.member.controller;

import com.example.minproj2_mybatis.member.dto.MemberDTO;
import com.example.minproj2_mybatis.member.entity.MemberEntity;
import com.example.minproj2_mybatis.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String singUp(){
        return "member/signForm";
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseEntity<?> insertMember(@Valid @RequestBody MemberDTO memberDTO, BindingResult bindingResult, Model model){
        log.info("=========> memberFormDTO : " + memberDTO);

        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });

            return ResponseEntity.badRequest().body(errors);
        }

        try {

            MemberEntity member = MemberEntity.createMember(memberDTO, passwordEncoder);
            int successResult = memberService.save(member);

            Map<String, Object> result  = new HashMap<>();

            result.put("SUCCESS", successResult);

            return ResponseEntity.ok(result);

        } catch (IllegalStateException e){
            Map<String, Object> result  = new HashMap<>();
            result.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }
}