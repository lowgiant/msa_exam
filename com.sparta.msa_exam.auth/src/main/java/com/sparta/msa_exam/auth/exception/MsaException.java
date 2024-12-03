package com.sparta.msa_exam.auth.exception;

import lombok.Getter;

@Getter
public class MsaException extends RuntimeException {
	private final ErrorCode errorCode;
	public MsaException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}
