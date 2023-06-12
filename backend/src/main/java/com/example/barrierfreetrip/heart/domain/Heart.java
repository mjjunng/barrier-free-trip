package com.example.barrierfreetrip.heart.domain;

import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.touristfacility.domain.TouristFacility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Heart {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "contentId")
    private TouristFacility touristFacility;

    public Heart (Member member, TouristFacility touristFacility) {
        this.member = member;
        this.touristFacility = touristFacility;
    }
}
