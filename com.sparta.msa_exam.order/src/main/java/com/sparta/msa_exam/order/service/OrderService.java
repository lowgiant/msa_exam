package com.sparta.msa_exam.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.msa_exam.order.dto.OrderAddRequest;
import com.sparta.msa_exam.order.dto.OrderAddResponse;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.mapper.OrderMapper;
import com.sparta.msa_exam.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

	private final OrderMapper orderMapper;
	private final OrderRepository orderRepository;


	public OrderAddResponse addOrder(OrderAddRequest request) {
		Order order = orderMapper.toEntity(request.getName());
		Order savedOrder = orderRepository.save(order);
		return orderMapper.toOrderAddResponse(savedOrder);
	}


}
