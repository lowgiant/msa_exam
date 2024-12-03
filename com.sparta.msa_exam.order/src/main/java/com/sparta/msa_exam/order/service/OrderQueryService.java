package com.sparta.msa_exam.order.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sparta.msa_exam.order.dto.OrderGetResponse;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.mapper.OrderMapper;
import com.sparta.msa_exam.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderQueryService {

	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;

	public OrderGetResponse getOrder(Long orderId) {
		Order order = findByOrderId(orderId);
		return orderMapper.toOrderGetResponse(order);
	}

	public Order findByOrderId(Long orderId) {
		return orderRepository.findById(orderId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
