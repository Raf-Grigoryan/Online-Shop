package org.example.onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.onlineshop.entity.product.Product;
import org.example.onlineshop.repository.ProductRepository;
import org.example.onlineshop.service.ProductService;
import org.hibernate.annotations.SortNatural;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Value("${online.shop.image.upload.path}")
    private String uploadPath;

    @Override
    public List<Product> productsByCategory(String categoryName) {
        return productRepository.findProductByCategoryName(categoryName);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @SneakyThrows
    public void saveProduct(Product product, MultipartFile multipartFile) {
        String fileName;
        if (!multipartFile.isEmpty()) {
            fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadPath, fileName);
            multipartFile.transferTo(file);
            product.setPictureName(fileName);
        }
        product.setCreatedAt(new Date());
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
