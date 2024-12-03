package com.sparta.msa_exam.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductAddRequest {

	final String name;

	@JsonProperty("supply_price")
	final Integer supplyPrice;

	@Builder
	public ProductAddRequest(String name, Integer supplyPrice) {
		this.name = name;
		this.supplyPrice = supplyPrice;
	}
}
