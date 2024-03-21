package com.triply.barrierfreetrip.member.repository;

import com.triply.barrierfreetrip.member.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>
{
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
    boolean existsByKeyEmail(String email);
    void deleteByKeyEmail(String email);
}
