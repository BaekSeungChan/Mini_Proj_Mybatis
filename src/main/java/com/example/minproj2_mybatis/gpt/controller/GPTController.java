package com.example.minproj2_mybatis.gpt.controller;

import com.example.minproj2_mybatis.gpt.dto.GPTRequest;
import com.example.minproj2_mybatis.gpt.dto.GPTResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/gpt")
@RequiredArgsConstructor
public class GPTController {

    @Value("${gpt.model}")
    private String model;

    @Value("${gpt.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;


    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt, Model model2){

        GPTRequest request = new GPTRequest(
                model,prompt,1,256,1,2,2);

        GPTResponse gptResponse = restTemplate.postForObject(
                apiUrl
                , request
                , GPTResponse.class
        );

        model2.addAttribute("gptResponse", gptResponse.getChoices().get(0).getMessage().getContent());


        return "finance/financeList";


    }
}