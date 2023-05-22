package com.example.barrierfreetrip.touristfacility.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TouristFacilityListResponseDto {
    private String contentId;
    private String contentTypeId;
    private String title;
    private String address;
    private Long rating;
    private String firstimg;
}
