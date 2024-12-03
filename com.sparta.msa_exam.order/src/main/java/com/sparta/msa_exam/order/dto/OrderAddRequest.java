package com.sparta.msa_exam.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderAddRequest {

	private final String name;

	@Builder
	@JsonCreator
	public OrderAddRequest(@JsonProperty String name) {
		this.name = name;
	}
}
