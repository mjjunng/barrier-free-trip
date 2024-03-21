package com.triply.barrierfreetrip.review.service;

import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.review.domain.Review;
import com.triply.barrierfreetrip.review.dto.ReviewListDto;
import com.triply.barrierfreetrip.review.repository.ReviewRepository;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacility;
import com.triply.barrierfreetrip.touristfacility.dto.TouristFacilityListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    @Override
    public void createReview(Member member, TouristFacility facility,
                             long rating, String content) {
        Review review = new Review(member, facility, rating, content);
        reviewRepository.save(review);
    }

    @Override
    public List<Review> findByContentId(TouristFacility facility) {
        return reviewRepository.findByContentId(facility);
    }
}
