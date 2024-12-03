package com.sparta.msa_exam.auth.common.config.token.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.msa_exam.auth.common.config.token.entity.TokenEntity;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
	Optional<TokenEntity> findByUserName(String username);
	Optional<TokenEntity> findByRefreshToken(String refreshToken);
	
}
