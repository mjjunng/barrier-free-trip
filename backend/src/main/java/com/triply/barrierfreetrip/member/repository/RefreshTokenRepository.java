package com.triply.barrierfreetrip.member.repository;

import com.triply.barrierfreetrip.member.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>
{
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
    boolean existsByKeyEmail(String email);

    @Modifying
    @Query("delete from RefreshToken rt where rt.keyEmail=:emails")
    void deleteByKeyEmail(@Param("emails") String email);

    @Modifying
    @Query("delete from RefreshToken rt where rt.refreshToken=:tokens")
    void deleteByRefreshToken(@Param("tokens") String refreshToken);
}
