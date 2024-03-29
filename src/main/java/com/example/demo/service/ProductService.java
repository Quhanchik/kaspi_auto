package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> getProductByProductId(String id) {
        return productRepository.getProductByProductId(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Page<Product> getAllByMarketIdPagination(String marketId, Integer page, Integer limit) {
        return productRepository.getAllByMarketId(marketId, PageRequest.of(page, limit));
    }
}
