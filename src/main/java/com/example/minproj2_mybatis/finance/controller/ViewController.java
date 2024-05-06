package com.example.minproj2_mybatis.finance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/finance")
public class ViewController {

    @GetMapping("/list")
    public String list(){
        return "finance/financeList";
    }
}
