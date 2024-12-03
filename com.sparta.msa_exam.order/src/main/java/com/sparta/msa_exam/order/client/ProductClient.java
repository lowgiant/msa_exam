package com.sparta.msa_exam.order.client;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.sparta.msa_exam.order.client.cofig.LoadBalancerConfig;

@FeignClient(name = "product-service")
@LoadBalancerClient(name = "product-service", configuration = LoadBalancerConfig.class)
public interface ProductClient {

	@GetMapping("/products")
	List<ProductGetResponse> getProducts();
}