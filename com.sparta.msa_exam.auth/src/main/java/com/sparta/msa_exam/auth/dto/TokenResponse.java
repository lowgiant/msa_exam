package com.sparta.msa_exam.auth.dto;

import lombok.Builder;

@Builder
public record TokenResponse(String accessToken, String refreshToken) {
}
