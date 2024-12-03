package com.sparta.msa_exam.product.repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.msa_exam.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Slice<Product> findAllBy(Pageable pageable);
}