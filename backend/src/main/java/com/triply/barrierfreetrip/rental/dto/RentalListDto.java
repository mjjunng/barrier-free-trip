package com.triply.barrierfreetrip.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalListDto {
    private Long id;
    private String title;
    private String addr;
    private String tel;
    private boolean like;

    public RentalListDto(Long id, String title, String addr, String tel) {
        this.id = id;
        this.title = title;
        this.addr = addr;
        this.tel = tel;
    }
}
