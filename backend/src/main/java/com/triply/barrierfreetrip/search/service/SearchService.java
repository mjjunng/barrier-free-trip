package com.triply.barrierfreetrip.search.service;

import com.triply.barrierfreetrip.search.dto.SearchDto;

import java.util.List;

public interface SearchService {
    public List<SearchDto> search(String keyword);
}
