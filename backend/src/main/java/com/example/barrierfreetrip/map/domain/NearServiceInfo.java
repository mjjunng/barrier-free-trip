package com.example.barrierfreetrip.map.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NearServiceInfo {
    private int type;
    private String title;
    private double mapX;
    private double mapY;
}
