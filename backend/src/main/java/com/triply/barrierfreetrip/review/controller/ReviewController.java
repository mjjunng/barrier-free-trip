package com.triply.barrierfreetrip.review.controller;

import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.review.domain.Review;
import com.triply.barrierfreetrip.review.dto.ReviewListDto;
import com.triply.barrierfreetrip.review.dto.ReviewRequestDto;
import com.triply.barrierfreetrip.review.service.ReviewService;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacility;
import com.triply.barrierfreetrip.touristfacility.service.TouristFacilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;
    private final TouristFacilityService touristFacilityService;

    @PostMapping("/reviews/{contentId}")
    public ResponseEntity saveReview(@PathVariable("contentId") String contentId,
                                      @RequestBody ReviewRequestDto requestData) {
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TouristFacility touristFacility = touristFacilityService.findByContentId(contentId);

        reviewService.createReview(member, touristFacility, requestData.getRating(), requestData.getContent());

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping("/reviews/{contentId}")
    public ResponseEntity getReview(@PathVariable("contentId") String contentId) {
        TouristFacility facility = touristFacilityService.findByContentId(contentId);
        List<Review> reviews = facility.getReviews();

        List<ReviewListDto> result = reviews.stream().map(rv -> new ReviewListDto(rv.getMember().getNickname(),
                rv.getRating(), rv.getContent())).collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();
        map.put("totalCnt", result.size());
        map.put("reviews", result);

        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
}
