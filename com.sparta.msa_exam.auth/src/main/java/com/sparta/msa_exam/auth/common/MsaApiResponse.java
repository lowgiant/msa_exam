package com.sparta.msa_exam.auth.common;

import com.sparta.msa_exam.auth.exception.ErrorCode;

public record MsaApiResponse<T> (
	boolean success,
	String code,
	String message,
	T data
) {
	public static final String CODE_SUCCEED = "SUCCEED";

	public static <T> MsaApiResponse<T> ok(T data) {
		return new MsaApiResponse<>(true, CODE_SUCCEED, null, data);
	}

	public static <T> MsaApiResponse<T> fail(ErrorCode errorCode, String message) {
		return new MsaApiResponse<>(false, errorCode.toString(), message, null);
	}
}
