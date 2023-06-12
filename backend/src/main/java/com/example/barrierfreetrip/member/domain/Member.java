package com.example.barrierfreetrip.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String email;

    public Member() {

    }

    public Member(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
