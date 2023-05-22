package com.example.barrierfreetrip.touristfacility.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TouristFacilityInfoResponseDto {
    private String contentId;
    private String contentTypeId;
    private String title;
    private String addr1;
    private String addr2;
    private String overview;
    private String homepage;
    private String tel;
    private String checkInTime;
    private String checkOutTime;
    private String parking;
    private Long rating;
    private String areaCode;
    private String sigunguCode;
    private String mapx;
    private String mapy;
    private List<String> imgs;
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
