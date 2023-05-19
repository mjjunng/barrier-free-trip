package com.example.barrierfreetrip.touristfacility.repository;

import com.example.barrierfreetrip.touristfacility.dto.TouristFacilityResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TouristFacilityRepositoryImpl implements TouristFacilityRepository {
    private final EntityManager em;

    @Override
    public List<TouristFacilityResponseDto> findByCode(String contentTypeId, String areaCode, String siginguCode) {
        return em.createQuery("select tf.contentId, tf.contentTypeId, tf.title, " +
                "tf.addr1, tf.rating from TouristFacility tf " +
                "where tf.contentTypeId=:contentTypeIds and tf.areaCode=:areaCodes " +
                        "and tf.sigunguCode=:siginguCodes", TouristFacilityResponseDto.class)
                .setParameter("contentTypeIds", contentTypeId)
                .setParameter("areaCodes", areaCode)
                .setParameter("siginguCodes", siginguCode)
                .getResultList();
    }
}
