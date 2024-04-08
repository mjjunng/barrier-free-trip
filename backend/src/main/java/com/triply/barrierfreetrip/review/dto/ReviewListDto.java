package com.triply.barrierfreetrip.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewListDto {
    private String nickname;
    private double rating;
    private String content;
}
