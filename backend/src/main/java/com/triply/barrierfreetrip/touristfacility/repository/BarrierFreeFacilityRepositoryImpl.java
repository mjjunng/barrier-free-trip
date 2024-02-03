package com.triply.barrierfreetrip.touristfacility.repository;

import com.triply.barrierfreetrip.touristfacility.domain.BarrierFreeFacility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BarrierFreeFacilityRepositoryImpl implements BarrierFreeFacilityRepository{
    private final EntityManager em;
    @Override
    public BarrierFreeFacility findByContentId(String contentId) {
        return em.createQuery("select bff from BarrierFreeFacility bff " +
                        "where bff.contentId=:contentIds", BarrierFreeFacility.class)
                .setParameter("contentIds", contentId)
                .getSingleResult();
    }
}
