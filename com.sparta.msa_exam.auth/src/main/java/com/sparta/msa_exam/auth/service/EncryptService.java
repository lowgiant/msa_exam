package com.sparta.msa_exam.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EncryptService {

	private final PasswordEncoder passwordEncoder;

	public String encrypt(String before) {
		return passwordEncoder.encode(before);
	}
}