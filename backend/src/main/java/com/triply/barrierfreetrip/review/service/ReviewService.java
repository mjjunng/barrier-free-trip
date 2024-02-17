package com.triply.barrierfreetrip.review.service;

import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.review.domain.Review;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacility;

import java.util.List;

public interface ReviewService {
    public Review createReview(Member member, TouristFacility facility,
                             double rating, String content);
    public List<Review> findByContentId(TouristFacility facility);
}
