package com.sparta.msa_exam.auth.domain.user.entity;

import com.sparta.msa_exam.auth.domain.user.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
	@Id
	@Column(name = "USER_NAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ROLE")
	@Enumerated(EnumType.STRING)
	Role role;

	@Builder
	public UserEntity(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	// public MsaUser toDomain() {
	// 	return MsaUser.builder()
	// 		.username(this.username)
	// 		.password(this.)
	// 		.build;
	//
	// }

	// public static UserEntity toEntity(CreateUser createUser) {
	// 	return new UserEntity(
	// 		createUser.getUsername(),
	// 		createUser.getEncryptedPassword(),
	// 		createUser.getEmail(),
	// 		createUser.getPhone()
	// 	);
	// }
}

