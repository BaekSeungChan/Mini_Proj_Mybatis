package com.example.minproj2_mybatis.member.controller;


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

//    @PostMapping("/new")
//    @ResponseBody
//    public ResponseEntity<?> insertMember(@Valid @RequestBody MemberDTO memberDTO, BindingResult bindingResult, Model model){
//        log.info("=========> memberFormDTO : " + memberDTO);
//
//        if(bindingResult.hasErrors()){
//            Map<String, String> errors = new HashMap<>();
//            bindingResult.getFieldErrors().forEach(error -> {
//                errors.put(error.getField(), error.getDefaultMessage());
//            });
//
//            return ResponseEntity.badRequest().body(errors);
//        }
//
//        try {
//
//            MemberEntity member = MemberEntity.createMember(memberDTO, passwordEncoder);
//            int successResult = memberService.save(member);
//
//            Map<String, Object> result  = new HashMap<>();
//
//            result.put("SUCCESS", successResult);
//
//            return ResponseEntity.ok(result);
//
//        } catch (IllegalStateException e){
//            Map<String, Object> result  = new HashMap<>();
//            result.put("error", e.getMessage());
//            return ResponseEntity.badRequest().body(result);
//        }
//    }

    @GetMapping("/login")
    public String login(){
        return "member/loginForm";
    }


    @GetMapping("/member/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호가 일치하지 않습니다.");
        return "member/loginForm";
    }

//    @GetMapping("/member/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication != null){
//            new SecurityContextLogoutHandler().logout(request, response, authentication);
//        }
//
//        return "redirect:/"; // 홈페이지로 리다이렉션
//    }

}