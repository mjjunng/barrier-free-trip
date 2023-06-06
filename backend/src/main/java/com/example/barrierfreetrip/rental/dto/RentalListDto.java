package com.example.barrierfreetrip.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentalListDto {
    private String title;
    private String addr;
    private String tel;
    private int like;

    public RentalListDto(String title, String addr, String tel) {
        this.title = title;
        this.addr = addr;
        this.tel = tel;
    }
}
