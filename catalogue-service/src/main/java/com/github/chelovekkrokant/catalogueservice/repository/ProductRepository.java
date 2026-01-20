package com.github.chelovekkrokant.catalogueservice.repository;


import com.github.chelovekkrokant.catalogueservice.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();

    Product save(Product product);

    Optional<Product> findById(Integer id);

    void deleteById(Integer id);
}
