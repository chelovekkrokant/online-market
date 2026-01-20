package com.github.chelovekkrokant.catalogueservice.repository;


import com.github.chelovekkrokant.catalogueservice.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Iterable<Product> findAllByTitleLikeIgnoreCase(String filter);
}
