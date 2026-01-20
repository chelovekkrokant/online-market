package com.github.chelovekkrokant.catalogueservice.service;


import com.github.chelovekkrokant.catalogueservice.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Iterable<Product> findAllProducts(String filter);

    Product createProduct(String title, String details);

    Optional<Product> findProductById(Integer productId);

    void updateProduct(Integer productId, String title, String details);

    void deleteProduct(Integer id);
}
