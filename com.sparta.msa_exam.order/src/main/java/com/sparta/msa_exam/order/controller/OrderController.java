package com.sparta.msa_exam.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.msa_exam.order.dto.OrderAddRequest;
import com.sparta.msa_exam.order.dto.OrderAddResponse;
import com.sparta.msa_exam.order.dto.OrderGetResponse;
import com.sparta.msa_exam.order.dto.OrderProductAddRequest;
import com.sparta.msa_exam.order.dto.OrderProductAddResponse;
import com.sparta.msa_exam.order.service.OrderProductService;
import com.sparta.msa_exam.order.service.OrderQueryService;
import com.sparta.msa_exam.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;
	private final OrderQueryService orderQueryService;
	private final OrderProductService orderProductService;

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderGetResponse> getOrder(
		@PathVariable Long orderId
	) {
		OrderGetResponse response =
			orderQueryService.getOrder(orderId);
		return ResponseEntity.ok()
			.body(response);
	}


	@PutMapping("/{orderId}")
	public ResponseEntity<OrderProductAddResponse> addOrderProduct(
		@PathVariable Long orderId,
		@RequestBody @Valid OrderProductAddRequest req
	) {
		OrderProductAddResponse response =
			orderProductService.addOrderProduct(orderId, req);
		return ResponseEntity.ok()
			.body(response);
	}


	@PostMapping
	public ResponseEntity<OrderAddResponse> addOrder(
		@RequestBody OrderAddRequest req
	) {
		OrderAddResponse response = orderService.addOrder(req);
		return ResponseEntity.ok()
			.body(response);
	}

}
