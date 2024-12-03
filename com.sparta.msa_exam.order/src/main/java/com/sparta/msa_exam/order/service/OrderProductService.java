package com.sparta.msa_exam.order.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sparta.msa_exam.order.client.ProductClient;
import com.sparta.msa_exam.order.client.ProductGetResponse;
import com.sparta.msa_exam.order.dto.OrderProductAddRequest;
import com.sparta.msa_exam.order.dto.OrderProductAddResponse;
import com.sparta.msa_exam.order.entity.Order;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderProductService {

	private final ProductClient productClient;
	private final OrderQueryService orderQueryService;

	@CircuitBreaker(name = "productService", fallbackMethod = "fallbackAddOrderProduct")
	public OrderProductAddResponse addOrderProduct(Long orderId, OrderProductAddRequest req) {
		Order order = orderQueryService.findByOrderId(orderId);
		validateProductId(req);
		order.addOrderProduct(req);
		return new OrderProductAddResponse(orderId, req.getProductId(), null);
	}

	public OrderProductAddResponse fallbackAddOrderProduct(Long orderId,
		OrderProductAddRequest req, Throwable throwable) {
		Order order = orderQueryService.findByOrderId(orderId);
		validateProductId(req);
		order.addOrderProduct(req);
		return new OrderProductAddResponse(
			orderId, req.getProductId(), "잠시 후에 주문 추가를 요청 해주세요");
	}



	private void validateProductId(OrderProductAddRequest req) {
		List<ProductGetResponse> productInfoList = productClient.getProducts();
		productInfoList.stream()
			.map(ProductGetResponse::getProductId)
			.filter(productId -> productId.equals(req.getProductId()))
			.findAny()
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}


}
