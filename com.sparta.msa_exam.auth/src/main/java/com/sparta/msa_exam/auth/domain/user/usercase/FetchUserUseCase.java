package com.sparta.msa_exam.auth.domain.user.usercase;

import com.sparta.msa_exam.auth.domain.user.dto.DetailUserResponse;

public interface FetchUserUseCase {

	DetailUserResponse findDetailUserByUserName(String username);

}
