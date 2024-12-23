package org.example.onlineshop.controller;

import lombok.RequiredArgsConstructor;
import org.example.onlineshop.entity.product.Category;
import org.example.onlineshop.entity.product.Product;
import org.example.onlineshop.service.CategoryService;
import org.example.onlineshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final CategoryService categoryService;

    private final ProductService productService;


    @GetMapping
    public String getAdminPage() {
        return "/admin/adminHome";
    }

    @GetMapping("/createCategory")
    public String getAddCategoryPage(ModelMap modelMap) {
        modelMap.put("categories", categoryService.findAll());
        return "/admin/adminCategory";
    }

    @PostMapping("/createCategory")
    public String addCategory(@ModelAttribute Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/createCategory";
    }

    @GetMapping("/product")
    public String getAdminProductPage(ModelMap modelMap) {
        modelMap.put("products", productService.getAllProducts());
        modelMap.put("categories", categoryService.findAll());
        return "/admin/adminProduct";
    }

    @PostMapping("/admin/createProduct")
    public String addProduct(@ModelAttribute Product product, @RequestParam("image") MultipartFile file) {
        productService.saveProduct(product, file);
        return "redirect:/admin/adminProduct";
    }


}
