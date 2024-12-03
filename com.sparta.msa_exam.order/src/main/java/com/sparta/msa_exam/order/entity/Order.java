package com.sparta.msa_exam.order.entity;

import java.util.ArrayList;
import java.util.List;

import com.sparta.msa_exam.order.dto.OrderProductAddRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@Column(name = "NANE", nullable = false)
	private String name;

	@OneToMany(
		mappedBy = "order",
		cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
		orphanRemoval = true
	)
	private final List<OrderProduct> orderProducts = new ArrayList<>();

	public void addOrderProduct(OrderProductAddRequest request) {

		OrderProductAddRequest addOrderProductRequest =
			new OrderProductAddRequest(this, request.getProductId());
		this.orderProducts.add(OrderProduct.from(addOrderProductRequest));
	}

	@Builder
	public Order(Long orderId, String name) {
		this.orderId = orderId;
		this.name = name;
	}
}
