package com.triply.barrierfreetrip.review.domain;

import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "touristFacilityContentId")
    private TouristFacility facility;

    private long rating;
    private String content;

    public Review(Member member, TouristFacility facility, long rating, String content) {
        this.member = member;
        this.facility = facility;
        this.rating = rating;
        this.content = content;
        facility.getReviews().add(this);
    }
}
