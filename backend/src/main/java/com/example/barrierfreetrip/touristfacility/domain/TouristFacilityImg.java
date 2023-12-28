package com.example.barrierfreetrip.touristfacility.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tourist_facility_img")
public class TouristFacilityImg {
    @Id
    private String serialnum;
	private String contentId;
	private String cpyrhtDivCd;
	private String originImgurl;
}
