package com.example.minproj2_mybatis.book.service;

import com.example.minproj2_mybatis.book.dto.response.BookDTO;
import com.example.minproj2_mybatis.book.dto.response.ResponseBookDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class BookSaleService {
    
    @Value("${X-Naver-Client-Id}")
    private String client_id;
    
    @Value("${X-Naver-Client-Secret}")
    private String secret_id;


    public List<BookDTO> bookDTOList(String text){

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query", text)
                .queryParam("display", 20)
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", client_id)
                .header("X-Naver-Client-Secret", secret_id)
                .build();


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseBookDTO responseBookDTO = null;

        try{
            responseBookDTO = objectMapper.readValue(resp.getBody(), ResponseBookDTO.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<BookDTO> books = responseBookDTO.getItems();

        return books;
    }

}
