package com.triply.barrierfreetrip.touristfacility.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "touristFacilityImg")
public class TouristFacilityImg {
    @Id
    private String serialnum;
	private String contentId;
	private String cpyrhtDivCd;
	private String originImgurl;
}
