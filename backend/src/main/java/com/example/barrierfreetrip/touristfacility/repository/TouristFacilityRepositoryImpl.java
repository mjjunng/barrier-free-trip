package com.example.barrierfreetrip.touristfacility.repository;

import com.example.barrierfreetrip.caretrip.domain.CareTrip;
import com.example.barrierfreetrip.touristfacility.dto.BarrierFreeFacility;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TouristFacilityRepositoryImpl implements TouristFacilityRepository {
    private final EntityManager em;

    @Override
    public List<TouristFacility> findByCode(String contentTypeId, String areaCode, String siginguCode) {
        return em.createQuery("select tf from TouristFacility tf " +
                "where tf.contentTypeId=:contentTypeIds and tf.areaCode=:areaCodes " +
                        "and tf.sigunguCode=:siginguCodes")
                .setParameter("contentTypeIds", contentTypeId)
                .setParameter("areaCodes", areaCode)
                .setParameter("siginguCodes", siginguCode)
                .getResultList();
    }

    @Override
    public List<String> findImgByContentId(String contentId) {
        return em.createQuery("select originImgurl from TouristFacilityImg tfi "
        + "where tfi.contentId=:contentIds")
                .setParameter("contentIds", contentId).getResultList();
    }

    @Override
    public TouristFacility findByContentId(String contentId) {
        return em.createQuery("select tf from TouristFacility tf " +
                "where tf.contentId=:contentIds", TouristFacility.class)
                .setParameter("contentIds", contentId)
                .getSingleResult();
    }

    @Override
    public Optional<TouristFacility> findByTitle(String keyword) {
        List<TouristFacility> touristFacilities = em.createQuery("select tf from TouristFacility tf " +
                        "where tf.title like concat('%',:keywords,'%') ")
                .setParameter("keywords", keyword)
                .getResultList();

        return touristFacilities.stream().findAny();
    }


}
