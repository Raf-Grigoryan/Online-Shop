package org.example.onlineshop.repository;

import org.example.onlineshop.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findProductByCategoryName(String categoryName);
}
