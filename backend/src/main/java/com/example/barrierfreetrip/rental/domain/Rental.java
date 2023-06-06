package com.example.barrierfreetrip.rental.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rental")
@Data
public class Rental {
    @Id
    private Long id;
    private String title;
	private String addr;
	private String tel;
	private String sido;
	private String sigungu;
}
