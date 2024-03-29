package com.triply.barrierfreetrip.charger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargerListDto {
    private Long id;
    private String title;
    private String addr;
    private String tel;
    private boolean like;

    public ChargerListDto(Long contentId, String title, String addr, String tel) {
        this.id = contentId;
        this.title = title;
        this.addr = addr;
        this.tel = tel;
    }
}
