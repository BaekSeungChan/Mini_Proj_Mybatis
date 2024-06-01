package com.example.minproj2_mybatis.book.controller;

import com.example.minproj2_mybatis.auth.entity.CustomMemberDetailsService;
import com.example.minproj2_mybatis.book.dto.request.BookCartRequest;
import com.example.minproj2_mybatis.book.dto.response.BookDTO;
import com.example.minproj2_mybatis.book.service.BookSaleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
@Slf4j
public class BookSaleController {

    private final BookSaleService bookSaleService;

    @GetMapping("/list")
    public String bookSale(Model model){

        List<BookDTO> bookDTOList = bookSaleService.bookDTOList("금융");

        model.addAttribute("books", bookDTOList);

        return "book/bookSaleList";
    }

    @GetMapping("/detail")
    public String bookDetail(@RequestParam("title") String title, Model model, Authentication authentication) {

        CustomMemberDetailsService customMemberDetailsService = (CustomMemberDetailsService) authentication.getPrincipal();
        List<BookDTO> bookDTOList = bookSaleService.bookDTOList(title);

        log.info("customMemberDetailsService = {} ", customMemberDetailsService );
        log.info("authentication.getPrincipal = {} ", authentication.getPrincipal().getClass() );

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

    List<BookCartRequest> bookCartRequests = new ArrayList<>();

    @GetMapping("/cart/orderInfo")
    public String orderInfo(@RequestParam("id") List<Long> ids,
                            @RequestParam("quantity") List<Integer> quantities,
                            @RequestParam("title") List<String> titles,
                            @RequestParam("discount") List<String> discounts,
                            @AuthenticationPrincipal CustomMemberDetailsService customMemberDetailsService,
                            Model model){

        List<BookCartRequest> selectedItems = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            BookCartRequest item = new BookCartRequest();
            item.setId(ids.get(i));
            item.setQuantity(quantities.get(i));
            item.setTitle(titles.get(i));
            item.setDiscount(discounts.get(i));
            selectedItems.add(item);
        }


        model.addAttribute("user", customMemberDetailsService.getName());
        model.addAttribute("orders", selectedItems);
        return "book/orderInfo";
    }

}