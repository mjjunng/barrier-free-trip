package com.example.barrierfreetrip.heart.repository;

import com.example.barrierfreetrip.heart.domain.Heart;
import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface HeartRepository {
    Heart findByIds(Member member, TouristFacility touristFacility);
    void save(Heart heart);

    int delete(Long hearId);

    Optional<Heart> findByIdsIfLikes(Member member, TouristFacility facility);

}
