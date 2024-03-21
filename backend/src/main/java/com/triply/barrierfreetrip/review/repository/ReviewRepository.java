package com.triply.barrierfreetrip.review.repository;

import com.triply.barrierfreetrip.review.domain.Review;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacility;

import java.util.List;

public interface ReviewRepository {
    public void save(Review review);
    public List<Review> findByContentId(TouristFacility facility);
}
