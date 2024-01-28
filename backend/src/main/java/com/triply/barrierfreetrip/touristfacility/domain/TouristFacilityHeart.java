package com.triply.barrierfreetrip.touristfacility.domain;

import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TouristFacilityHeart {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "touristFacilityContentId")
    private TouristFacility touristFacility;

    public TouristFacilityHeart(Member member, TouristFacility touristFacility) {
        this.member = member;
        this.touristFacility = touristFacility;
    }
}
