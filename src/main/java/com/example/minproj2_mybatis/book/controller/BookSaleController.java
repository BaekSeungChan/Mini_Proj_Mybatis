package com.example.minproj2_mybatis.book.controller;

import com.example.minproj2_mybatis.book.dto.response.BookDTO;
import com.example.minproj2_mybatis.book.service.BookSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String bookDetail(@RequestParam("title") String title, Model model) {
        List<BookDTO> bookDTOList = bookSaleService.bookDTOList(title);

        if (!bookDTOList.isEmpty()) {
            model.addAttribute("book", bookDTOList.get(0));
        }

        return "book/bookDetail";
    }

}
