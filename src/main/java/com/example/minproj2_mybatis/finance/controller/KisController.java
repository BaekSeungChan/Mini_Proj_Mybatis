package com.example.minproj2_mybatis.finance.controller;


import com.example.minproj2_mybatis.finance.dto.ResponseOutputDTO;
import com.example.minproj2_mybatis.finance.service.KisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class KisController {

    private final KisService kisService;


    @Autowired
    public KisController(KisService kisService) {
        this.kisService = kisService;
    }

    @GetMapping("/volume-rank")
    public Mono<ResponseEntity<List<ResponseOutputDTO>>> getVolumeRank() {
        return kisService.getVolumeRank()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}