package com.sparta.msa_exam.auth.dto;

import lombok.Getter;

@Getter
public class SignInRequest {
	private final String username;
	private final String password;

	public SignInRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
}