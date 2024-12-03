package com.sparta.msa_exam.order.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderProductAddResponse {

	private final Long orderId;
	private final Long productId;
	private final String message;

	@Builder
	public OrderProductAddResponse(Long orderId, Long productId, String message) {
		this.orderId = orderId;
		this.productId = productId;
		this.message = message;
	}
}
