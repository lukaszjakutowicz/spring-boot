package com.lukaszjakutowicz.redisdemo.cache.service;

import com.lukaszjakutowicz.redisdemo.cache.entity.Product;
import com.lukaszjakutowicz.redisdemo.cache.repository.ProductCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductCacheRepository productCacheRepository;

    public boolean saveProduct(Product product) {
        return productCacheRepository.save(product);
    }

    public List<Product> fetchAllProduct() {
        return productCacheRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productCacheRepository.findById(id);
    }

    public boolean deleteProduct(Long id) {
        return productCacheRepository.deleteProduct(id);
    }

    public boolean updateProduct(Long id, Product Product) {
        return productCacheRepository.updateProduct(id,Product);
    }
}
