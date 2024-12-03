package com.sparta.msa_exam.auth.common.config.token.jwt;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;



@Component
public class JWTUntil {
	private SecretKey secretKey;

	@Value("${jwt.secret-key}")
	private String secret;

	@PostConstruct
	public void init() {
		secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
			Jwts.SIG.HS256.key().build().getAlgorithm());
	}


	public String getUsername(String token) {
		return Jwts.parser()
			.verifyWith(secretKey).build()
			.parseSignedClaims(token)
			.getPayload()
			.get("username", String.class);
	}

	public String getRole(String token) {
		return Jwts.parser()
			.verifyWith(secretKey).build()
			.parseSignedClaims(token)
			.getPayload()
			.get("role", String.class);
	}

	public Boolean isExpired(String token) {
		return Jwts.parser()
			.verifyWith(secretKey).build()
			.parseSignedClaims(token)
			.getPayload()
			.getExpiration()
			.before(new Date());
	}

	public Date getExpirationFromToken(String token) {
		return Jwts.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token)
			.getPayload()
			.getExpiration();
	}



	public String createJwtToken(String username, String role, Duration expireAt) {
		Date now = new Date();
		Instant instant = now.toInstant();

		return Jwts.builder()
			.claim("username", username)
			.claim("role", role)
			.issuedAt(now)
			.expiration(Date.from(instant.plus(expireAt)))
			.signWith(secretKey)
			.compact();
	}
}
