package com.example.barrierfreetrip.caretrip.domain;


import com.example.barrierfreetrip.heart.domain.Heart;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "care_trip_service")
public class CareTrip {
    @Id
    private Long id;
    private String title;
    private String tel;
    private String sido;
    private String sigungu;
    private String addr;

}
