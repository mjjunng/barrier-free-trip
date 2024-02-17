package com.triply.barrierfreetrip.charger.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wheelchairCharger")
@Data
public class Charger {

    @Id
    private Long id;
    private String title;
    private String sido;
    private String sigungu;
    private String areaCode;
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

    private double mapx;
    private double mapy;

}
