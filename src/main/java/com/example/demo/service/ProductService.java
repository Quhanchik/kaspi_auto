package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> getProductByProductId(String productId, Integer sellerId) {
        return productRepository.getProductsByProductIdAndSellerId(productId, sellerId);
    }

    public void save(Product product) {
        productRepository.save(product);
    }
}
