package com.triply.barrierfreetrip.touristfacility.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Embeddable
public class AreaCodeId implements Serializable {
    private String areaCd;

    private String sigunguCd;
}
