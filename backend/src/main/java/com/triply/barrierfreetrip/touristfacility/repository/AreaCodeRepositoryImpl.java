package com.triply.barrierfreetrip.touristfacility.repository;


import com.triply.barrierfreetrip.touristfacility.domain.AreaCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AreaCodeRepositoryImpl implements AreaCodeRepository{
    private final EntityManager em;
    @Override
    public List<AreaCode> getSidoCode() {
        return em.createQuery("select ac from AreaCode ac where ac.areaCodeId.sigunguCd='0'")
                .getResultList();
    }

    @Override
    public List<AreaCode> findByAreaCode(String areaCode) {
        return em.createQuery("select ac from AreaCode ac where ac.areaCodeId.areaCd=:areaCds " +
                        "and ac.areaCodeId.sigunguCd != '0'")
                .setParameter("areaCds", areaCode)
                .getResultList();
    }
}
