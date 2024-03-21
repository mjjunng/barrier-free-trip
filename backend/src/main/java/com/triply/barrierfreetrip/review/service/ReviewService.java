package com.triply.barrierfreetrip.review.service;

import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.review.domain.Review;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacility;

import java.util.List;

public interface ReviewService {
    public void createReview(Member member, TouristFacility facility,
                             long rating, String content);
    public List<Review> findByContentId(TouristFacility facility);
}
