package com.sparta.msa_exam.auth.domain.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sparta.msa_exam.auth.domain.user.dto.DetailUserResponse;
import com.sparta.msa_exam.auth.domain.user.dto.MsaUser;
import com.sparta.msa_exam.auth.domain.user.usercase.FetchUserUseCase;
import com.sparta.msa_exam.auth.domain.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MsaUserDetailService implements UserDetailsService {

	private final FetchUserUseCase fetchUserUseCase;
	private final UserMapper userMapper;
	@Override
	public UserDetails loadUserByUsername(String username) throws
		UsernameNotFoundException {
		DetailUserResponse user = fetchUserUseCase.findDetailUserByUserName(username);
		return new MsaUser(userMapper.DetailUsertoResEntity(user));
	}
}
