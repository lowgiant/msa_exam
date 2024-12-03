package com.sparta.msa_exam.order.client.cofig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;


import reactor.core.publisher.Flux;

public class WeightedServiceInstanceListSupplier implements
	ServiceInstanceListSupplier {

	private final Random random = new Random();
	private final DiscoveryClient discoveryClient;

	public WeightedServiceInstanceListSupplier(ConfigurableApplicationContext context) {
		this.discoveryClient = context.getBean(DiscoveryClient.class);
	}

	@Override
	public String getServiceId() {
		return "product-service";
	}

	@Override
	public Flux<List<ServiceInstance>> get() {
		return Flux.defer(() -> {
			List<ServiceInstance> instances = discoveryClient.getInstances(getServiceId());
			return Flux.just(selectInstances(instances));
		});
	}

	private List<ServiceInstance> selectInstances(List<ServiceInstance> instances) {
		List<ServiceInstance> selectedInstances = new ArrayList<>();
		int totalWeight = 100;
		int randomWeight = random.nextInt(totalWeight);

		if (randomWeight < 70) {
			selectedInstances.add(instances.get(0));
		} else {
			selectedInstances.add(instances.get(1));
		}

		return selectedInstances;
	}
}
