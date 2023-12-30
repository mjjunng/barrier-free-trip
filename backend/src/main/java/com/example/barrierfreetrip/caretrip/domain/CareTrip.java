package com.example.barrierfreetrip.caretrip.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "careTripService")
public class CareTrip {
    @Id
    private Long id;
    private String title;
    private String tel;
    private String sido;
    private String sigungu;
    private String addr;

}
