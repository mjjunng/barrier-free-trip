package com.triply.barrierfreetrip.touristfacility.domain;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbjCode")
@Data
public class AreaCode {

    @EmbeddedId
    private AreaCodeId areaCodeId;
    private String cdNm;
}
