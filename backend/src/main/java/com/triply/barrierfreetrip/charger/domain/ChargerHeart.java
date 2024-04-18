package com.triply.barrierfreetrip.charger.domain;

import com.triply.barrierfreetrip.member.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "chargerHeart")
@Data
@NoArgsConstructor
public class ChargerHeart {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "chargerId")
    private Charger charger;

    public ChargerHeart(Member member, Charger charger) {
        this.member = member;
        this.charger = charger;
    }
}
