package com.sparta.msa_exam.order.client;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductGetResponse {
	private final Long productId;
	private final String name;
	private final Integer supply_price;


	@Builder
	public ProductGetResponse(Long productId, String name,
		Integer supply_price) {
		this.productId = productId;
		this.name = name;
		this.supply_price = supply_price;
	}
}
