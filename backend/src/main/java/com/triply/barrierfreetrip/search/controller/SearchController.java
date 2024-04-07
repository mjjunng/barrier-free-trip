package com.triply.barrierfreetrip.search.controller;

import com.triply.barrierfreetrip.search.dto.SearchDto;
import com.triply.barrierfreetrip.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/search/{keyword}")
    public ResponseEntity search(@PathVariable("keyword") String keyword) {
        List<SearchDto> result = searchService.search(keyword);

        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

}
