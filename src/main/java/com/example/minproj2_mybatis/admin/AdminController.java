package com.example.minproj2_mybatis.admin;

import com.example.minproj2_mybatis.member.dto.MemberDTO;
import com.example.minproj2_mybatis.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/list")
    public String list(Model model){
        List<MemberDTO> memberEntityList = adminService.findAll();

        for(var result : memberEntityList){
            System.out.println("ddd " + result);
        }

        model.addAttribute("member", memberEntityList);

        return "admin/list";
    }
}
