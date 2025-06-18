package com.ms.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ms.product.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}