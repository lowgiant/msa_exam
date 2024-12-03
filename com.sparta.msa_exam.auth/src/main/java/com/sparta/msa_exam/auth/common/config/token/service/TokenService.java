package com.sparta.msa_exam.auth.common.config.token.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.msa_exam.auth.common.config.token.entity.TokenEntity;
import com.sparta.msa_exam.auth.common.config.token.jwt.JWTUntil;
import com.sparta.msa_exam.auth.common.config.token.mapper.TokenMapper;
import com.sparta.msa_exam.auth.common.config.token.repository.TokenRepository;
import com.sparta.msa_exam.auth.dto.TokenResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenService {

	private final TokenRepository tokenRepository;
	private final JWTUntil jwtUntil;
	private final TokenMapper tokenMapper;


	@Value("${jwt.expire.access-token}")
	private int accessTokenExpireHour;

	@Value("${jwt.expire.refresh-token}")
	private int refreshTokenExpireHour;

	public TokenResponse upsertToken(String username, String role) {
		return tokenRepository.findByUserName(username)
			.map(token -> updateNewToken(username, role))
			.orElseGet(() -> createNewToken(username, role));
	}

	public TokenResponse createNewToken(String username, String role) {
		String accessToken = jwtUntil.createJwtToken(username, role, Duration.ofHours(accessTokenExpireHour));
		String refreshToken = jwtUntil.createJwtToken(username, role, Duration.ofHours(refreshTokenExpireHour));

		Date refreshTokenExpiresAt = jwtUntil.getExpirationFromToken(refreshToken);
		TokenEntity tokenEntity = tokenMapper.toTokenEntity(username,refreshToken,
			refreshTokenExpiresAt);

		tokenRepository.save(tokenEntity);

		return TokenResponse.builder()
			.accessToken(accessToken)
			.refreshToken(tokenEntity.getRefreshToken())
			.build();
	}


	public TokenResponse updateNewToken(String username, String role) {
		TokenEntity tokenEntity = tokenRepository.findByUserName(username).orElseThrow();

		String accessToken = jwtUntil.createJwtToken(username, role, Duration.ofHours(accessTokenExpireHour));
		String refreshToken = jwtUntil.createJwtToken(username, role, Duration.ofHours(refreshTokenExpireHour));

		tokenEntity.updateToken(refreshToken);
		tokenRepository.save(tokenEntity);

		return TokenResponse.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	public TokenResponse reissueToken(String accessToken, String refreshToken){
		String userName = jwtUntil.getUsername(accessToken);
		String role = jwtUntil.getRole(accessToken);
		Optional<TokenEntity> byUserName = tokenRepository.findByUserName(userName);
		if (byUserName.isEmpty()) {
			throw new RuntimeException("토큰이 유효하지 않습니다.");
		}

		TokenEntity MsaToken = byUserName.get();
		if (!MsaToken.getRefreshToken().equals(refreshToken)) {
			throw new RuntimeException("리프레시 토큰이 유효하지 않습니다.");
		}

		return updateNewToken(userName, role);
	}




}
