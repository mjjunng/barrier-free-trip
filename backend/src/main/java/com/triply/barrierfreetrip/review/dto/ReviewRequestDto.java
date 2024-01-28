package com.triply.barrierfreetrip.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {
    private long rating;
    private String content;
}
