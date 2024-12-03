package com.sparta.msa_exam.product.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sparta.msa_exam.product.dto.ProductAddRequest;
import com.sparta.msa_exam.product.dto.ProductAddResponse;
import com.sparta.msa_exam.product.dto.ProductGetRequest;
import com.sparta.msa_exam.product.dto.ProductGetResponse;
import com.sparta.msa_exam.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductAddResponse>
    addProduct(@RequestBody @Valid ProductAddRequest request) {
        ProductAddResponse response =
            productService.addProduct(request);
        return ResponseEntity.ok()
            .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductGetResponse>> getProductList(
        ProductGetRequest request, Pageable pageable) {
        List<ProductGetResponse> productList =
            productService.getProductList(request, pageable);
        return ResponseEntity.ok()
            .body(productList);
    }

}
