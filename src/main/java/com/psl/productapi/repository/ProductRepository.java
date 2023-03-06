package com.psl.productapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psl.productapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}