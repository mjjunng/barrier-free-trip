package com.example.barrierfreetrip.touristfacility.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "barrierFreeFacility")
public class BarrierFreeFacility {
    @Id
    private String contentId;
    private String wheelchair;
    private String _exit;
    private String elevator;
    private String restroom;
    private String guidesystem;
    private String blindhandicapetc;
    private String signguide;
    private String videoguide;
    private String hearingroom;
    private String hearinghandicapetc;
    private String stroller;
    private String lactationroom;
    private String babysparechair;
    private String infantsfamilyetc;
    private String auditorium;
    private String room;
    private String handicapetc;
    private String braileblock;
    private String helpdog;
    private String guidehuman;
    private String audioguide;
    private String bigprint;
    private String brailepromotion;
    private String freeParking;
    private String route;
    private String publictransport;
    private String ticketoffice;
    private String promotion;
}
