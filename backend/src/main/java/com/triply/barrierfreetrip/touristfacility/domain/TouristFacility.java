package com.triply.barrierfreetrip.touristfacility.domain;

import com.triply.barrierfreetrip.review.domain.Review;
import lombok.Data;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@Table(name = "touristFacility")
@Data
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
	private double rating;
	private String areaCode;
	private String sigunguCode;
	private double mapx;
	private double mapy;

	private String firstimage;

	@OneToMany(mappedBy = "facility")
	private List<Review> reviews;
}
