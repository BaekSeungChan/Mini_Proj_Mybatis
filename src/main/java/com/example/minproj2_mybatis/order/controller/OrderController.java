package com.example.minproj2_mybatis.order.controller;

import com.example.minproj2_mybatis.order.dto.OrderDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/save")
    public String orderDone(@RequestBody OrderDTO orderDTO){
        return null;
    }
}
