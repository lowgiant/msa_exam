package com.sparta.msa_exam.order.mapper;

import org.springframework.stereotype.Component;

import com.sparta.msa_exam.order.dto.OrderAddResponse;
import com.sparta.msa_exam.order.dto.OrderGetResponse;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderProduct;

@Component
public class OrderMapper {
	public OrderGetResponse toOrderGetResponse(Order order){
		return OrderGetResponse.builder()
			.orderId(order.getOrderId())
			.OrderProducts(order.getOrderProducts()
								.stream()
								.map(OrderProduct::getProductId)
								.toList())
			.build();

	}

	public OrderAddResponse toOrderAddResponse(Order order){
		return OrderAddResponse.builder()
			.orderId(order.getOrderId())
			.name(order.getName())
			.products(order.getOrderProducts()
				.stream()
				.map(OrderProduct::getProductId)
				.toList())
			.build();

	}

	public Order toEntity(String name){
		return Order.builder()
			.name(name)
			.build();
	}

}
