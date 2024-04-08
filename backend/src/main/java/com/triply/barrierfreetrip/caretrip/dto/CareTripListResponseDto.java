package com.triply.barrierfreetrip.caretrip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CareTripListResponseDto {
    private Long id;
    private String title;
    private String addr;
    private String tel;
    private boolean like;
}
