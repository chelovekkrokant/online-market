package com.github.chelovekkrokant.managerapp.service;

import com.github.chelovekkrokant.managerapp.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();

    Product createProduct(String title, String details);

    Optional<Product> findProductById(Integer productId);

    void updateProduct(Integer productId, String title, String details);

    void deleteProduct(Integer id);
}
