package com.triply.barrierfreetrip.touristfacility.dto;

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
    private String addr;
    private Long rating;
    private String firstimg;
    private String tel;
    private boolean like;

    public TouristFacilityListResponseDto(String contentId, String contentTypeId, String title, String addr, Long rating, String firstimg, String tel) {
        this.contentId = contentId;
        this.contentTypeId = contentTypeId;
        this.title = title;
        this.addr = addr;
        this.rating = rating;
        this.firstimg = firstimg;
        this.tel = tel;
    }
}
