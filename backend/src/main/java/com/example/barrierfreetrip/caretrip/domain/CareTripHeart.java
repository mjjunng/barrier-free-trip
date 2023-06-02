package com.example.barrierfreetrip.caretrip.domain;

import com.example.barrierfreetrip.member.domain.Member;

import javax.persistence.*;

@Entity
@Table(name = "care_trip_heart")
public class CareTripHeart {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne()
    @JoinColumn(name = "careTripId")
    private CareTrip careTrip;

}
