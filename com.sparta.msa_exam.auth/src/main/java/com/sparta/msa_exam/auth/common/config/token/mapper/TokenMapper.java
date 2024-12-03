package com.sparta.msa_exam.auth.common.config.token.mapper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.sparta.msa_exam.auth.common.config.token.entity.TokenEntity;

@Component
public class TokenMapper {


	public TokenEntity toTokenEntity(String username, String refreshToken, Date refreshTokenExpiresAt){
		LocalDateTime localDateTimeExpiresAt = refreshTokenExpiresAt.toInstant()
		.atZone(ZoneId.systemDefault())
		.toLocalDateTime();

		return TokenEntity.builder()
			.userName(username)
			.refreshToken(refreshToken)
			.refreshTokenExpiresAt(localDateTimeExpiresAt)
			.build();
	}
}
