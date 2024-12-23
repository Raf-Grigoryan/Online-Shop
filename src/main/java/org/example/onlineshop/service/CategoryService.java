package org.example.onlineshop.service;


import org.example.onlineshop.entity.product.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    void deleteCategory(int id);

    void addCategory(Category category);

}
