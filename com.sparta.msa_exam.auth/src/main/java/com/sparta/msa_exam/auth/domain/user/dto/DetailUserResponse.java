package com.sparta.msa_exam.auth.domain.user.dto;

import lombok.Builder;

@Builder
public record DetailUserResponse(
	String username,
	String password,
	String Role
){

}

