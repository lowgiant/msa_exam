package com.sparta.msa_exam.order.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderAddResponse {
	private final Long orderId;
	private final String name;
	private final List<Long> products;

	@Builder
	public OrderAddResponse(Long orderId, String name, List<Long> products) {
		this.orderId = orderId;
		this.name = name;
		this.products = products;
	}
}
