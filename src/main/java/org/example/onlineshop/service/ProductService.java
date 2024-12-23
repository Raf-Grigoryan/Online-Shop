package org.example.onlineshop.service;

import org.example.onlineshop.entity.product.Product;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;


public interface ProductService {

    List<Product> productsByCategory(String categoryName);

    List<Product> getAllProducts();

    void saveProduct(Product product, MultipartFile file);

    void deleteProduct(int id);
}
