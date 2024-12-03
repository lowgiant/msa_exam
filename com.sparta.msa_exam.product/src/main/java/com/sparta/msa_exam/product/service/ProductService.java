package com.sparta.msa_exam.product.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sparta.msa_exam.product.dto.ProductAddRequest;
import com.sparta.msa_exam.product.dto.ProductAddResponse;
import com.sparta.msa_exam.product.dto.ProductGetRequest;
import com.sparta.msa_exam.product.dto.ProductGetResponse;
import com.sparta.msa_exam.product.entity.Product;
import com.sparta.msa_exam.product.mapper.ProductMapper;
import com.sparta.msa_exam.product.repository.ProductRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

	private final ProductMapper productMapper;
	private final ProductRepository productRepository;

	@CacheEvict(cacheNames = "productList"
		, allEntries = true)
	@CachePut(cacheNames = "productList"
		, key = "#result.productId")
	public ProductAddResponse addProduct(ProductAddRequest request) {
		Product product = productMapper.toEntity(request);
		Product addedProduct = productRepository.save(product);
		return productMapper.toAddProductRes(addedProduct);
	}

	@Cacheable(
		cacheNames = "productList",
		key = "{ #pageable.pageNumber, #pageable.pageSize }"
	)
	public List<ProductGetResponse> getProductList(
		ProductGetRequest request, Pageable pageable
	) { return productRepository.findAllBy(pageable)
			.map(productMapper::toGetProductRes)
			.toList();
	}

}