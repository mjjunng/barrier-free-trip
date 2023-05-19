package com.example.barrierfreetrip.touristfacility.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TouristFacility {

    @Id
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

}
