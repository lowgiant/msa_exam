package com.sparta.msa_exam.auth.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sparta.msa_exam.auth.common.MsaApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

	@ExceptionHandler(MsaException.class)
	protected MsaApiResponse<?> handleSecurityException(MsaException e) {
		log.error("error={}", e.getMessage(), e);
		return MsaApiResponse.fail(e.getErrorCode(), e.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	protected MsaApiResponse<?> handleRuntimeException(RuntimeException e) {
		log.error("error={}", e.getMessage(), e);
		return MsaApiResponse.fail(ErrorCode.DEFAULT_ERROR, e.getMessage());
	}
}
