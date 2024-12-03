package com.sparta.msa_exam.auth.domain.user.mapper;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.sparta.msa_exam.auth.domain.user.dto.DetailUserResponse;
import com.sparta.msa_exam.auth.domain.user.dto.SignUpRequest;
import com.sparta.msa_exam.auth.domain.user.entity.UserEntity;
import com.sparta.msa_exam.auth.domain.user.enums.Role;

import jakarta.annotation.PostConstruct;

@Component
public class UserMapper {

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void init() {
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}

	public UserEntity DetailUsertoResEntity(DetailUserResponse res){
		return UserEntity.builder()
			.username(res.username())
			.password(res.password())
			.role(Role.fromKey(res.Role()))
			.build();
	}


	public UserEntity toCustomer(SignUpRequest dto) {
		return UserEntity.builder()
			.username(dto.username())
			.password(bCryptPasswordEncoder.encode(dto.password()))
			.role(Role.CUSTOMER)
			.build();
	}
}
