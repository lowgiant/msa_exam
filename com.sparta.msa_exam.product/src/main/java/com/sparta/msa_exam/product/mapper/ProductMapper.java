package com.sparta.msa_exam.product.mapper;

import org.springframework.stereotype.Component;

import com.sparta.msa_exam.product.dto.ProductAddRequest;
import com.sparta.msa_exam.product.dto.ProductAddResponse;
import com.sparta.msa_exam.product.dto.ProductGetResponse;
import com.sparta.msa_exam.product.entity.Product;

@Component
public class ProductMapper {

	public ProductGetResponse toGetProductRes(Product product){
		return ProductGetResponse.builder()
			.productId(product.getId())
			.name(product.getName())
			.supplyPrice(product.getSupplyPrice())
			.build();
	}

	public ProductAddResponse toAddProductRes(Product product){
		return ProductAddResponse.builder()
			.productId(product.getId())
			.name(product.getName())
			.supplyPrice(product.getSupplyPrice())
			.build();
	}

	public Product toEntity(ProductAddRequest req){
		return Product.builder()
			.name(req.getName())
			.supplyPrice(req.getSupplyPrice())
			.build();

	}

}
