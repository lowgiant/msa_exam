package com.sparta.msa_exam.auth.domain.user.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.sparta.msa_exam.auth.domain.user.dto.DetailUserResponse;
import com.sparta.msa_exam.auth.domain.user.dto.SignUpRequest;
import com.sparta.msa_exam.auth.domain.user.entity.UserEntity;
import com.sparta.msa_exam.auth.domain.user.mapper.UserMapper;
import com.sparta.msa_exam.auth.domain.user.repository.UserRepository;
import com.sparta.msa_exam.auth.domain.user.usercase.FetchUserUseCase;
import com.sparta.msa_exam.auth.dto.SignInRequest;
import com.sparta.msa_exam.auth.exception.UserException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements FetchUserUseCase {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public DetailUserResponse findDetailUserByUserName(String username) {
		Optional<UserEntity> byUserName = userRepository.findByUsername(username);
		if (byUserName.isEmpty()) {
			throw new UserException.UserDoesNotExistException();
		}
		UserEntity msaUser = byUserName.get();

		return DetailUserResponse.builder()
			.username(msaUser.getUsername())
			.password(msaUser.getPassword())
			.Role(msaUser.getRole().getKey())
			.build();
	}

	public void authProcess(SignUpRequest dto){
		UserEntity customerUser = userMapper.toCustomer(dto);
		userRepository.save(customerUser);
	}


}
