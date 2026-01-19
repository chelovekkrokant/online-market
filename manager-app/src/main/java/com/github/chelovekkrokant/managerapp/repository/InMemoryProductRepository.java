package com.github.chelovekkrokant.managerapp.repository;

import com.github.chelovekkrokant.managerapp.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.IntStream;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final List<Product> products = Collections.synchronizedList(new LinkedList<>());

    public InMemoryProductRepository() {
        IntStream.range(0,5).forEach(i -> {
            products.add(new Product(i, "Товар №%d".formatted(i), "Описание товара №%d".formatted(i)));
        });
    }

    @Override
    public List<Product> findAll() {
        return Collections.unmodifiableList(this.products);
    }

    @Override
    public Product save(Product product) {
        product.setId(
                this.products.stream()
                        .max(Comparator.comparingInt(Product::getId))
                        .map(Product::getId)
                        .orElse(0) + 1);
        this.products.add(product);
        return product;
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return this.products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    @Override
    public void deleteById(Integer id) {
        this.products.removeIf(product -> product.getId().equals(id));
    }
}
