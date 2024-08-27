package com.lukaszjakutowicz.redisdemo.cache.repository;

import com.lukaszjakutowicz.redisdemo.cache.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductCacheRepository {
    private static final String PRODUCT_CACHE_KEY = "PRODUCT";

    private final RedisTemplate<String, Product> redisTemplate;

    public List<Product> findAll() {
        Set<String> keys = redisTemplate.keys(PRODUCT_CACHE_KEY + "*");
        return keys.stream()
                .map(key -> {
                    Long id = Long.valueOf(key.split(":")[1]);
                    return findById(id);
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public Optional<Product> findById(Long id) {
        log.info("Find product by id: {}", id);
        try {
            Product product = (Product) redisTemplate.opsForHash().get(getProductCacheKey(id), id);
            return Optional.ofNullable(product);
        } catch (Exception e) {
            log.error("Failed to find product by id: {}", id, e);
            return Optional.empty();
        }
    }

    public boolean save(Product product) {
        log.info("Saving product: {}", product);

        try {
            redisTemplate.opsForHash().put(getProductCacheKey(product.getId()), product.getId(), product);
            return true;
        } catch (Exception e) {
            log.error("Error while saving product: {}", product, e);
            return false;
        }
    }

    public boolean deleteProduct(Long id) {
        log.info("Deleting product: {}", id);

        try {
            redisTemplate.opsForHash().delete(getProductCacheKey(id), id);
            return true;
        } catch (Exception e) {
            log.error("Error while deleting product: {}", id, e);
            return false;
        }
    }

    public boolean updateProduct(Long id, Product product) {
        log.info("Updating product: {}", product);

        try {
            redisTemplate.opsForHash().put(getProductCacheKey(id), id, product);
            return true;
        } catch (Exception e) {
            log.error("Error while updating product: {}", product, e);
            return false;
        }
    }

    private String getProductCacheKey(Long id) {
        return PRODUCT_CACHE_KEY + ":" + id;
    }
}
