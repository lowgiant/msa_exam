package com.sparta.msa_exam.product.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductAddResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	final Long productId;
	final String name;
	final Integer supplyPrice;
}

