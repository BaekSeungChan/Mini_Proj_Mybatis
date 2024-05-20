package com.example.minproj2_mybatis.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myprofile")
public class MyProfileController {

    @GetMapping("/page")
    public String profilePage(){

        return "member/myprofile";
    }

    @GetMapping("/edit")
    public String profileEdit(){

        return "member/profileEdit";
    }
}
