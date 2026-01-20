package com.github.chelovekkrokant.catalogueservice.service;

import com.github.chelovekkrokant.catalogueservice.entity.Product;
import com.github.chelovekkrokant.catalogueservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String details) {
         return this.productRepository.save(new Product(null, title, details));
    }

    @Override
    public Optional<Product> findProductById(Integer productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    public void updateProduct(Integer productId, String title, String details) {
        this.productRepository.findById(productId)
                .ifPresentOrElse(product -> {
                    product.setTitle(title);
                    product.setDetails(details);
                    this.productRepository.save(product);
                }, () -> {
                    throw new RuntimeException("Product not found with id: " + productId);
                });
    }

    @Override
    public void deleteProduct(Integer id) {
        this.productRepository.deleteById(id);
    }


}
