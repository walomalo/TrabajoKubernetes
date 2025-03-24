package com.netec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netec.app.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long > {
}
