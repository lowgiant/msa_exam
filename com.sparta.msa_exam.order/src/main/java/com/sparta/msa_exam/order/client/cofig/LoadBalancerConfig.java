package com.sparta.msa_exam.order.client.cofig;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class LoadBalancerConfig {
	@Bean
	@Primary
	public ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext context) {
		return new WeightedServiceInstanceListSupplier(context);
	}
}
