package com.example.minproj2_mybatis.admin;

import com.example.minproj2_mybatis.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/delete")
    public ResponseEntity<String> deleteMember(@RequestParam("id") Long id) {

        System.out.println("id : " + id);
        try {
            adminService.delete(id);
            return ResponseEntity.ok("삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제를 실패했습니다.");
        }
    }
}
