package com.triply.barrierfreetrip.review.service;

import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.review.domain.Review;
import com.triply.barrierfreetrip.review.dto.ReviewListDto;
import com.triply.barrierfreetrip.review.repository.ReviewRepository;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacility;
import com.triply.barrierfreetrip.touristfacility.dto.TouristFacilityListResponseDto;
import com.triply.barrierfreetrip.touristfacility.service.TouristFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final TouristFacilityService touristFacilityService;
    @Override
    public Review createReview(Member member, TouristFacility facility,
                             double rating, String content) {
        Review review = new Review(member, facility, rating, content);
        reviewRepository.save(review);

        // 평점 update
        touristFacilityService.updateRating(facility, rating);

        return review;
    }

    @Override
    public List<Review> findByContentId(TouristFacility facility) {
        return reviewRepository.findByContentId(facility);
    }
}
