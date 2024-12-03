package com.sparta.msa_exam.order.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderGetResponse {

	private final Long orderId;
	private final List<Long> OrderProducts;

}
