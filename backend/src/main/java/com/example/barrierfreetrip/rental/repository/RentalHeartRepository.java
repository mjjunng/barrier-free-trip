package com.example.barrierfreetrip.rental.repository;

import com.example.barrierfreetrip.charger.domain.Charger;
import com.example.barrierfreetrip.charger.domain.ChargerHeart;
import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.rental.domain.Rental;
import com.example.barrierfreetrip.rental.domain.RentalHeart;

import java.util.Optional;

public interface RentalHeartRepository {
    public void save(RentalHeart rentalHeart);
    public int delete(Long heartId);
    public Optional<RentalHeart> findByIds(Member member, Rental rental);
    public Optional<RentalHeart> findByIdsIfLikes(Member member, Rental rental);
}
