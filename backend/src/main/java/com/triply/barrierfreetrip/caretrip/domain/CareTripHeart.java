package com.triply.barrierfreetrip.caretrip.domain;

import com.triply.barrierfreetrip.member.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "careTripHeart")
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
