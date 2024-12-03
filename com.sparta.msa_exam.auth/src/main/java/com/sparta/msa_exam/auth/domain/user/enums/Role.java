package com.sparta.msa_exam.auth.domain.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
	CUSTOMER("ROLE_CUSTOMER","고객"),
	OWNER("ROLE_OWNER","판매자"),
	MANAGER("ROLE_MANAGER","관리자"),
	MASTER("ROLE_MASTER","마스터");

	private final String key;
	private final String description;

	public static Role fromKey(String key) {
		for (Role role : values()) {
			if (role.getKey().equals(key)) {
				return role;
			}
		}
		throw new IllegalArgumentException("Invalid role key: " + key);
	}

}
