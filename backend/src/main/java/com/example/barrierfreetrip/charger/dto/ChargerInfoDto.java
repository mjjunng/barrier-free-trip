package com.example.barrierfreetrip.charger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargerInfoDto {
    private String title;
    private String addr;
    private String weekdayOpen;
    private String weekdayClose;
    private String weekendOpen;
    private String weekendClose;
    private String holidayOpen;
    private String holidayClose;
    private String possible;
    private String air;
    private String phoneCharge;
    private String tel;
    private int like;

}
