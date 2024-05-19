package com.example.minproj2_mybatis.book.controller;

import com.example.minproj2_mybatis.auth.entity.CustomMemberDetailsService;
import com.example.minproj2_mybatis.book.dto.request.BookCartRequest;
import com.example.minproj2_mybatis.book.dto.response.BookDTO;
import com.example.minproj2_mybatis.book.service.BookSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        if (result > 0) {
            return ResponseEntity.ok("{\"message\": \"Success\"}");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Fail\"}");
        }
    }

    @GetMapping("/cart/list")
    public String cartList(Model model, @AuthenticationPrincipal CustomMemberDetailsService customMemberDetailsService){

        List<BookDTO> bookDTOS = new ArrayList<>();

        if(customMemberDetailsService != null){
            String name = customMemberDetailsService.getName();
            bookDTOS = bookSaleService.findByName(name);
        }

        model.addAttribute("books", bookDTOS);

        return "book/bookCartList";
    }

    @GetMapping("/cart/remove")
    public String cartDelete(@RequestParam("id") Long id){
        bookSaleService.delete(id);

        return "redirect:/book/cart/list";
    }


}
