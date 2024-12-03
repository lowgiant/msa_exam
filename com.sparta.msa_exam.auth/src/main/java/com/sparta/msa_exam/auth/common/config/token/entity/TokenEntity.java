package com.sparta.msa_exam.auth.common.config.token.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "tokens")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenEntity {
	@Id
	@Column(name = "TOKEN_ID")
	private String tokenId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "REFRESH_TOKEN")
	private String refreshToken;

	@Column(name = "REFRESH_TOKEN_EXPIRES_AT")
	private LocalDateTime refreshTokenExpiresAt;

	@Builder
	private TokenEntity(String userName, String refreshToken, LocalDateTime refreshTokenExpiresAt) {
		this.tokenId = UUID.randomUUID().toString();
		this.userName = userName;
		this.refreshToken = refreshToken;
		this.refreshTokenExpiresAt = refreshTokenExpiresAt;
	}

	public void updateToken( String refreshToken) {
		LocalDateTime now = LocalDateTime.now();
		this.refreshToken = refreshToken;
		this.refreshTokenExpiresAt = getRefreshTokenExpiredAt(now);
	}


	private static LocalDateTime getRefreshTokenExpiredAt(LocalDateTime now) {
		return now.plusHours(24);
	}
}