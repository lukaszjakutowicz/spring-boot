package com.lukaszjakutowicz.redisdemo.api.controller;

import com.lukaszjakutowicz.redisdemo.cache.entity.Product;
import com.lukaszjakutowicz.redisdemo.cache.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<String> saveUser(@RequestBody Product product) {
        boolean result = productService.saveProduct(product);
        return result
                ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> fetchAllUser() {
        List<Product> products = productService.fetchAllProduct();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> fetchUserById(@PathVariable("id") Long id) {
        Optional<Product> optionalProduct = productService.findById(id);
        return optionalProduct.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        return productService.deleteProduct(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
