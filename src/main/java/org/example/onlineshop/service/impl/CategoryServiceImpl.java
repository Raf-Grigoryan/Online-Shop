package org.example.onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.onlineshop.entity.product.Category;
import org.example.onlineshop.repository.CategoryRepository;
import org.example.onlineshop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
}
