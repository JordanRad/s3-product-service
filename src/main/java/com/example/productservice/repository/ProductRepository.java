package com.example.productservice.repository;

import com.example.productservice.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {
    Product findByName(String name);
}
