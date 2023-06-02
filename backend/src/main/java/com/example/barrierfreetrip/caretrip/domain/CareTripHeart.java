package com.example.barrierfreetrip.caretrip.domain;

import com.example.barrierfreetrip.member.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "care_trip_heart")
@Data
@NoArgsConstructor
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

    public CareTripHeart(Member member, CareTrip careTrip) {
        this.member = member;
        this.careTrip = careTrip;
    }
}
