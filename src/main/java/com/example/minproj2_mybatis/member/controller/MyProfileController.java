package com.example.minproj2_mybatis.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myprofile")
public class MyProfileController {

    @GetMapping("/")
    public String profilePage(){

        return null;
    }
}
