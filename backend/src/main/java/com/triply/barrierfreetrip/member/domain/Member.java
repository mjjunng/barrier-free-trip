package com.triply.barrierfreetrip.member.domain;

import com.triply.barrierfreetrip.caretrip.domain.CareTripHeart;
import com.triply.barrierfreetrip.charger.domain.ChargerHeart;
import com.triply.barrierfreetrip.rental.domain.RentalHeart;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacilityHeart;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Member implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<TouristFacilityHeart> touristFacilityHearts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<CareTripHeart> careTripHearts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ChargerHeart> chargerHearts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<RentalHeart> rentalHearts = new ArrayList<>();

    public Member(String email, String nickname, List<String> roles) {
        this.email = email;
        this.nickname = nickname;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
