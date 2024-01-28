package com.triply.barrierfreetrip.search;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchDto {
    private String title;
    private String addr;
    private String tel;
    private String firstImage;
    private Long rating;
}
