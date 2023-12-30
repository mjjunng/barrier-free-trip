package com.example.barrierfreetrip.rental.domain;

import com.example.barrierfreetrip.charger.domain.Charger;
import com.example.barrierfreetrip.member.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "rentalHeart")
@Data
@NoArgsConstructor
public class RentalHeart {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "rentalId")
    private Rental rental;

    public RentalHeart(Member member, Rental rental) {
        this.member = member;
        this.rental = rental;
    }
}
