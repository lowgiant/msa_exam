package com.sparta.msa_exam.order.dto;

import com.sparta.msa_exam.order.entity.Order;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderProductAddRequest {
	private final Order order;
	private final Long productId;

	@Builder
	public OrderProductAddRequest(Order order, Long productId) {
		this.order = order;
		this.productId = productId;
	}
}
