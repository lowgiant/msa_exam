package com.sparta.msa_exam.auth.exception;
public class UserException extends MsaException {
	public UserException(ErrorCode errorCode) {
		super(errorCode);
	}

	public static class UserAlreadyExistException extends UserException {
		public UserAlreadyExistException() {
			super(ErrorCode.USER_ALREADY_EXIST);
		}
	}

	public static class UserDoesNotExistException extends UserException {
		public UserDoesNotExistException() {
			super(ErrorCode.USER_DOES_NOT_EXIST);
		}
	}
}