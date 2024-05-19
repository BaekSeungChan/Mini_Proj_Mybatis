package com.example.minproj2_mybatis.book.controller;

import com.example.minproj2_mybatis.auth.entity.CustomMemberDetailsService;
import com.example.minproj2_mybatis.book.dto.request.BookCartRequest;
import com.example.minproj2_mybatis.book.dto.response.BookDTO;
import com.example.minproj2_mybatis.book.service.BookSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookSaleController {

    private final BookSaleService bookSaleService;

    @GetMapping("/list")
    public String bookSale(Model model){

        List<BookDTO> bookDTOList = bookSaleService.bookDTOList("금융");

        model.addAttribute("books", bookDTOList);

        return "book/bookSaleList";
    }

    @GetMapping("/detail")
    public String bookDetail(@RequestParam("title") String title, Model model, @AuthenticationPrincipal CustomMemberDetailsService customMemberDetailsService) {

        List<BookDTO> bookDTOList = bookSaleService.bookDTOList(title);


        if (!bookDTOList.isEmpty() || customMemberDetailsService != null) {
            String name = customMemberDetailsService.getName();

            model.addAttribute("book", bookDTOList.get(0));
            model.addAttribute("user", name);
        }

        return "book/bookDetail";
    }

    @PostMapping("/cart/save")
    @ResponseBody
    public ResponseEntity<String> cartSave(@RequestBody BookCartRequest bookCartRequest) {
        int result = bookSaleService.save(bookCartRequest);

        System.out.println("bookCartRequest :" + bookCartRequest);
        if (result > 0) {
            return ResponseEntity.ok("{\"message\": \"Success\"}");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Fail\"}");
        }
    }

//    BookCartRequest(username=naver zZG5DNld3I3BpbU3nvgamuPQ1UJUA9qbDyC7Y7q0l3Q, title=2024 SD에듀 투자자산운용사 실제유형 모의고사+특별부록 PASSCODE Premium ver 6.0 (24년 기본서 개정사항 및 기출분석(19년 ~ 23년 + 24년 3월 시험) 완벽 반영 / 프리미엄 강의노트(2회분) / 특별부록Ⅰ 빈출포인트 O/X퀴즈 + Ⅱ 실제유형문제 추가 풀(100문항)), link=https://search.shopping.naver.com/book/catalog/47016218622, image=https://shopping-phinf.pstatic.net/main_4701621/47016218622.20240414071108.jpg, author=유창호, discount=49500, publisher=시대고시기획, pubdate=20240506, isbn=9791138371407, quantity=1)
}
