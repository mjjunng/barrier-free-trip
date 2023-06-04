package com.example.barrierfreetrip.charger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChargerListDto {
    private String title;
    private String addr;
    private String tel;
    private int like;

    public ChargerListDto(String title, String addr, String tel) {
        this.title = title;
        this.addr = addr;
        this.tel = tel;
    }
}
